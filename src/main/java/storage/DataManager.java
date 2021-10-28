package storage;

import service.BudgetManager;
import service.ExpenseManager;
import service.IncomeManager;
import service.LoadableManager;

import java.io.IOException;

public class DataManager {

    private static DataManager dataMgr;
    private LoadableManager[] mgrs;

    public static DataManager getDataMgr() {
        if (dataMgr == null) {
            dataMgr = new DataManager();
        }
        return dataMgr;
    }

    private DataManager() {
        mgrs = new LoadableManager[3];
        mgrs[0] = BudgetManager.getBudgetMgr();
        mgrs[1] = ExpenseManager.getExpenseMgr();
        mgrs[2] = IncomeManager.getIncomeManager();
    }

    public void loadAllManagers() throws IOException {
        DataIntegrityChecker.check();

        for (LoadableManager mgr : mgrs) {
            String label = mgr.getFileLabel();
            String filename = DataLocation.dataFilenames.get(label);

            String[] dataString = DataReader.read(filename);
            mgr.parse(dataString);
        }
    }

    public void write() throws IOException {
        DataIntegrityChecker.check();

        for (LoadableManager mgr : mgrs) {
            String label = mgr.getFileLabel();
            String filename = DataLocation.dataFilenames.get(label);
            String dataString = mgr.toFileString();
            DataWriter.write(filename, dataString);
        }
    }
}
