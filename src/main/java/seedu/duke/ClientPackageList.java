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
        packages.add(pack);
        packageCount++;
    }

}
