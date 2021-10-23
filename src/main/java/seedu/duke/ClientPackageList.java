package seedu.duke;

import java.util.ArrayList;

public class ClientPackageList {
    private static ArrayList<ClientPackage> clientPackages;
    private static int packageCount = 0;

    public ClientPackageList() {
        clientPackages = new ArrayList<>();
        packageCount = 0;
    }

    public void add(ClientPackage pack) {
        packageCount++;
        clientPackages.add(pack);
        System.out.println(clientPackages.get(0));
    }

    public ClientPackage get(int index) {
        return clientPackages.get(index);
    }

    public int getPackageCount() {
        return packageCount;
    }

}
