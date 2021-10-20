package seedu.duke;

import java.util.ArrayList;

public class PackageList {
    private static ArrayList<Package> packages;
    private static int packageCount = 0;

    public PackageList() {
        packages = new ArrayList<>();
        packageCount = 0;
    }

    public void add(Package pack) {
        packages.add(pack);
        packageCount++;
    }

}
