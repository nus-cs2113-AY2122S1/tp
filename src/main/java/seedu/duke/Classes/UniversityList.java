package seedu.duke.Classes;

import java.util.ArrayList;

public class UniversityList {
    protected ArrayList<University> list;

    public UniversityList(ArrayList<University> list) {
        this.list = list;
    }

    public void addUniversity(University addedUniversity) {
        list.add(addedUniversity);
    }

    public void removeUniversity(String universityName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(universityName)) {
                list.remove(i);
                break;
            }
        }
    }

    public void removeUniversity(int index) {
        list.remove(index);
    }

    public void listUniversities() {
        for (int i = 0; i < list.size(); i++) {
            University curr = list.get(i);
            String output = (i + 1) + ". " + curr.name;
            System.out.println(output);
        }
    }

    public int getSize() {
        return list.size();
    }

    public University get(int index) {
        return list.get(index);
    }
}