package entity.invest;


import java.util.ArrayList;

public class SavingList {
    private static ArrayList<Saving> savings = new ArrayList<>();

    public static void addSavings(Saving saving) {
        String savingToAddName = saving.getName();

        for (int i = 0; i < savings.size(); i++) {
            Saving s = savings.get(i);
            String currName = s.getName();

            if (currName.equals(savingToAddName)) {
                s.add(saving);
                return;
            }
        }

        savings.add(saving);
    }

    public static void deleteSavings(int incomeIndex) throws IndexOutOfBoundsException {
        savings.remove(incomeIndex);
    }

    public static ArrayList<Saving> getAllSavings() {
        return savings;
    }

}
