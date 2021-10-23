package seedu.duke;

import java.util.ArrayList;

public class ClientPackageList {
    private static ArrayList<ClientPackage> packages;
    private static int packageCount = 0;

    public ClientPackageList() {
        packages = new ArrayList<>();
        packageCount = 0;
    }

    public void add(ClientPackage pack) {
        packageCount++;
        packages.add(pack);
    }

    public ClientPackage get(int index) {
        return packages.get(index);
    }

    public int getPackageCount() {
        return packageCount;
    }

}
