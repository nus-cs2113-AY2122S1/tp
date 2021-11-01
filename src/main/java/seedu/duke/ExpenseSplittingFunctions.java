package seedu.duke;

import java.util.HashMap;

public abstract class ExpenseSplittingFunctions {
    private static final double EPSILON = 0.001;

    //@@author joshualeeky
    protected static void updateOnePersonSpending(Expense expense, Person person) {
        person.setMoneyOwed(person, expense.getAmountSpent());
        expense.setPayer(person);
        expense.setAmountSplit(person, expense.getAmountSpent());
    }

    protected static void updateIndividualSpending(Expense expense) throws CancelExpenseException {
        Person payer = getValidPersonInExpenseFromString(expense);
        expense.setPayer(payer);
        HashMap<Person, Double> amountBeingPaid = new HashMap<>();
        Ui.equalSplitPrompt();
        double total = 0.0;
        for (Person person : expense.getPersonsList()) {
            double amountRemaining = expense.getAmountSpent() - total;
            if (amountRemaining < EPSILON) {
                assignZeroToRemaining(expense, amountBeingPaid, payer);
                return;
            } else if (checkLastPersonInExpense(expense, person)) {
                assignRemainder(person, payer, amountRemaining, expense, amountBeingPaid);
                return;
            }
            Ui.printHowMuchDidPersonSpend(person.getName(), amountRemaining);
            String amountString = Storage.getScanner().nextLine().strip();
            if (amountString.equalsIgnoreCase("-cancel")) {
                throw new CancelExpenseException();
            } else if (checkAssignEqual(amountBeingPaid, amountString)) {
                assignEqualAmounts(payer, expense, amountBeingPaid);
                return;
            } else {
                try {
                    double amount = Double.parseDouble(amountString);
                    amount = Storage.formatForeignMoneyDouble(amount);
                    total += amount;
                    if (total > expense.getAmountSpent()) {
                        Ui.printIncorrectAmount(expense.getAmountSpent());
                        updateIndividualSpending(expense);
                        return;
                    } else {
                        amountBeingPaid.put(person, amount);
                    }
                } catch (NumberFormatException e) {
                    Ui.argNotNumber();
                    updateIndividualSpending(expense);
                    return;
                }
            }
        }
        if (total < expense.getAmountSpent()) {
            Ui.printIncorrectAmount(expense.getAmountSpent());
            updateIndividualSpending(expense);
        } else {
            assignAmounts(payer, expense, amountBeingPaid);
        }
    }

    private static boolean checkAssignEqual(HashMap<Person, Double> amountBeingPaid, String amountString) {
        return amountString.equalsIgnoreCase("equal") && amountBeingPaid.isEmpty();
    }

    private static boolean checkLastPersonInExpense(Expense expense, Person person) {
        return expense.getPersonsList().indexOf(person) == expense.getPersonsList().size() - 1;
    }

    private static void assignZeroToRemaining(Expense expense, HashMap<Person, Double> amountBeingPaid, Person payer)
            throws CancelExpenseException {
        Ui.askUserToConfirm();
        if (Parser.getUserToConfirm()) {
            for (Person person : expense.getPersonsList()) {
                if (!amountBeingPaid.containsKey(person)) {
                    amountBeingPaid.put(person, 0d);
                }
            }
            assignAmounts(payer, expense,amountBeingPaid);
        } else {
            // Print error
            updateIndividualSpending(expense);
        }
    }

    private static void assignRemainder(Person person, Person payer, double amountRemaining, Expense expense,
                                        HashMap<Person, Double> amountBeingPaid) throws CancelExpenseException {
        Ui.askAutoAssignRemainder(person, amountRemaining);
        if (Parser.getUserToConfirm()) {
            amountBeingPaid.put(person, Storage.formatForeignMoneyDouble(amountRemaining));
            assignAmounts(payer, expense, amountBeingPaid);
        } else {
            Ui.printIncorrectAmount(expense.getAmountSpent()); //Change to different error message
            updateIndividualSpending(expense);
        }
    }

    private static Person getValidPersonInExpenseFromString(Expense expense) throws CancelExpenseException {
        Ui.printGetPersonPaid();
        String name = Storage.getScanner().nextLine().strip();
        if (name.equalsIgnoreCase("-cancel")) {
            throw new CancelExpenseException();
        }
        for (Person person : expense.getPersonsList()) {
            if (name.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        Ui.printPersonNotInExpense();
        Ui.printPeopleInvolved(expense.getPersonsList());
        getValidPersonInExpenseFromString(expense);
        return null;
    }

    private static void assignEqualAmounts(Person payer, Expense expense, HashMap<Person, Double> amountBeingPaid) {
        double total = 0.0;
        double amount = Storage.formatForeignMoneyDouble(expense.getAmountSpent() / expense.getPersonsList().size());
        for (Person people : expense.getPersonsList()) {
            amountBeingPaid.put(people, amount);
            total += amount;
        }
        //This will cause payer to bear the deficit or surplus
        if (total != expense.getAmountSpent()) {
            double payerAmount = amountBeingPaid.get(payer) + (expense.getAmountSpent() - total);
            amountBeingPaid.put(payer, payerAmount);
        }
        assignAmounts(payer, expense, amountBeingPaid);
    }

    private static void assignAmounts(Person payer, Expense expense, HashMap<Person, Double> amountBeingPaid) {
        for (Person person : expense.getPersonsList()) {
            if (person == payer) {
                person.setMoneyOwed(person, amountBeingPaid.get(person));
                expense.setAmountSplit(person, amountBeingPaid.get(person));
                continue;
            }
            payer.setMoneyOwed(person, amountBeingPaid.get(person));
            person.setMoneyOwed(payer, -amountBeingPaid.get(person));
            person.setMoneyOwed(person, amountBeingPaid.get(person));
            expense.setAmountSplit(person, amountBeingPaid.get(person));
        }
    }
}
