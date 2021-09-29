package seedu.module;

import java.util.ArrayList;

public class ModList {

    private ArrayList<Mod> modList;

    public ModList() {
        this.modList = new ArrayList<Mod>();
    }

    public int getSize() {
        return modList.size();
    }

    public void addMod(Mod mod) {
        modList.add(mod);
    }

    public Mod getMod(int index) {
        return modList.get(index);
    }

    public void clearMods() {
        modList.clear();
    }
}