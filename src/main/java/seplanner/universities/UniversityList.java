package seplanner.universities;

import java.util.ArrayList;

public class UniversityList {

    protected ArrayList<University> list;

    public UniversityList(ArrayList<University> list) {
        this.list = list;
    }

    public UniversityList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a university with the given name to the list.
     * @param addedUniversity Name of the university to be added
     */
    public void addUniversity(University addedUniversity) {
        assert addedUniversity != null;
        list.add(addedUniversity);
        list.sort(new University());
        assert !list.isEmpty();
    }

    /**
     * Removes the university with the given name from the list.
     * @param universityName Name of the university to be removed
     */
    public void removeUniversity(String universityName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).name.equals(universityName)) {
                list.remove(i);
                break;
            }
        }
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<University> getList() {
        return list;
    }

    /**
     * Looks for the university at a specific index of the list.
     * @param index Index to look from the list
     * @return The university object at that index position in the list
     */
    public University get(int index) {
        assert index < list.size();
        return list.get(index);
    }

    /**
     * Checks whether a university exists in the list.
     * @param universityName Name of the university
     * @return true if the university exists, false otherwise
     */
    public boolean isExistUniversity(String universityName) {
        for (University uni : list) {
            if (uni.name.equals(universityName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds for the university object with the given name.
     * @param universityName Name of the university
     * @return The university object with the given name, null if there is no university with such name in the list
     */
    public University getUniversity(String universityName) {
        for (University university : list) {
            if (university.name.equals(universityName)) {
                return university;
            }
        }
        return null;
    }

}