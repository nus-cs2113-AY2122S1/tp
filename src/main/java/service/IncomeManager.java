package service;

public class IncomeManager {
    private static IncomeManager incomeMgr;

    private IncomeManager() {
    }

    public static IncomeManager getIncomeManager() {
        if(incomeMgr == null)
            incomeMgr = new IncomeManager();

        return incomeMgr;
    }
}
