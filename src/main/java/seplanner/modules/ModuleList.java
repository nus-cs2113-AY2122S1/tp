package seplanner.modules;

import java.util.ArrayList;

public class ModuleList {
    protected ArrayList<Module> list;

    public ModuleList(ArrayList<Module> list) {
        this.list = list;
    }

    public ModuleList() {
        this.list = new ArrayList<>();
    }

    public void addModule(Module module) {
        assert module != null;
        list.add(module);
        list.sort(new Module());
        assert !list.isEmpty();
    }

    public void removeModule(String moduleCode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).moduleCode.equals(moduleCode)) {
                list.remove(i);
                break;
            }
        }
    }

    public void removeModule(int index) {
        list.remove(index);
    }

    public int getSize() {
        return list.size();
    }

    public Module get(int index) {
        assert index <= list.size();
        return list.get(index);
    }

    public boolean isModuleExist(String moduleCode) {
        for (Module module : list) {
            if (module.moduleCode.equals(moduleCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean isModuleExist(Module searchModule) {
        for (Module module : list) {
            if (module.isEqual(searchModule)) {
                return true;
            }
        }
        return false;
    }

    public Module getModule(String moduleCode) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).moduleCode.equals(moduleCode)) {
                return list.get(i);
            }
        }
        return null;
    }

    public ArrayList<Module> getList() {
        return list;
    }

    public void listModules() {
        assert list.size() != 0;
        for (int i = 0; i < list.size(); i++) {
            Module curr = list.get(i);
            String output = (i + 1) + ". " + curr.moduleName;
            System.out.println(output);
        }
    }
}