package seedu.duke.Classes;

import java.util.ArrayList;

public class University {
    protected String name;
    protected ArrayList<ModuleMapping> list;

    public University(String name, ArrayList<ModuleMapping> list) {
        this.name = name;
        this.list = list;
    }

    public void addMapping(Module localModule, Module mappedModule) {
        list.add(new ModuleMapping(localModule, mappedModule));
    }

    public void listMappings() {
        for (int i = 0; i < list.size(); i++) {
            ModuleMapping currentMapping = list.get(i);
            String output = currentMapping.localModule + " - " + currentMapping.mappedModule;
            System.out.println(output);
        }
    }

    public String getName() {
        return name;
    }

}