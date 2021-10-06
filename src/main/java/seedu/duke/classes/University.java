package seedu.duke.classes;

import java.util.ArrayList;

public class University {
    protected String name;
    protected ArrayList<Classes.ModuleMapping> list;

    public University(String name, ArrayList<Classes.ModuleMapping> list) {
        this.name = name;
        this.list = list;
    }

    public void addMapping(java.lang.Module localModule, java.lang.Module mappedModule) {
        list.add(new Classes.ModuleMapping(localModule, mappedModule));
    }

    public void listMappings() {
        for (int i = 0; i < list.size(); i++) {
            Classes.ModuleMapping currentMapping = list.get(i);
            String output = currentMapping.localModule + " - " + currentMapping.mappedModule;
            System.out.println(output);
        }
    }

    public String getName() {
        return name;
    }

}