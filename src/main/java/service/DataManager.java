package service;

public class DataManager {
    private static DataManager dataMgr;

    private DataManager() {
    }

    public static DataManager getDataManager() {
        if(dataMgr == null)
            dataMgr = new DataManager();

        return dataMgr;
    }
}
