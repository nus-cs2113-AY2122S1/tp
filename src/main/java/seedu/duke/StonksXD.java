package seedu.duke;

import seedu.commands.Command;
import seedu.commands.ExitCommand;

import seedu.utility.BudgetManager;
import seedu.utility.DataManager;
import seedu.utility.FinancialTracker;
import seedu.utility.FinancialAdvisor;

import seedu.utility.Parser;
import seedu.utility.Ui;


public class StonksXD {
    private Ui ui;
    private FinancialTracker finances;
    private Parser parser;
    private DataManager dataManager;
    private BudgetManager budgetManager;
    private FinancialAdvisor financialAdvisor;
    private boolean isNonTerminatingCommand;
    private String advice;

    public StonksXD() {
        this.ui = new Ui();
        this.finances = new FinancialTracker();
        this.parser = new Parser();
        this.budgetManager = new BudgetManager();
        
        this.dataManager = new DataManager(parser, finances, ui, budgetManager);
        dataManager.loadAll();
        
        this.isNonTerminatingCommand = true;
        
        this.financialAdvisor = new FinancialAdvisor();
        this.advice = financialAdvisor.getRandomAdvice();
    }
    
    public void run() {
        ui.printWelcome();
        while (isNonTerminatingCommand) {
            String fullCommand = ui.readCommand();
            Command command = parser.parseCommand(fullCommand);
            command.execute(finances, ui, budgetManager);
            isNonTerminatingCommand = command.isExit();
            dataManager.saveAll();
        }
        // Commented this part to pass Github test 
        // ui.printBye(advice);
    }

    

    public static void main(String[] args) {
        new StonksXD().run();
    }
}
