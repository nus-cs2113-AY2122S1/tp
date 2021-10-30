package taa.classmodel;

//@@author leyondlee
import taa.ClassChecker;

import java.util.ArrayList;

public class ClassList implements ClassChecker {
    private final ArrayList<ClassObject> classes;

    public ClassList() {
        this.classes = new ArrayList<>();
    }

    public int getSize() {
        return classes.size();
    }

    /**
     * Gets the list of classes. Note: This returns a new ArrayList instance.
     *
     * @return A new ArrayList containing all the classes.
     */
    public ArrayList<ClassObject> getClasses() {
        return new ArrayList<>(classes);
    }

    /**
     * Checks if an index is valid with respect to the classes list.
     *
     * @param index The index to check.
     * @return true if valid, else false.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
    }

    /**
     * Adds a ClassObject object to the list of classes.
     *
     * @param classObject The ClassObject object to add.
     */
    public void addClass(ClassObject classObject) {
        classes.add(classObject);
    }

    /**
     * Gets a class at the specified index.
     *
     * @param index The index of the class.
     * @return A ClassObject object if index is valid, else null.
     */
    public ClassObject getClassAt(int index) {
        if (isValidIndex(index)) {
            return classes.get(index);
        }

        return null;
    }

    /**
     * Gets the class from the list with the specified id.
     *
     * @param id The class id to search for.
     * @return A Class object if found, else null.
     */
    public ClassObject getClassWithId(String id) {
        for (ClassObject classObject : classes) {
            if (classObject.getId().equals(id)) {
                return classObject;
            }
        }

        return null;
    }

    /**
     * Deletes a particular class from the list.
     *
     * @param classObject The class object to delete.
     * @return true if successfully removed, else false.
     */
    public boolean deleteClass(ClassObject classObject) {
        assert classObject != null;

        return classes.remove(classObject);
    }

    public void deleteAllClasses() {
        classes.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getSize(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(classes.get(i));
        }

        return stringBuilder.toString();
    }

    /**
     * Checks if the variables in the class are valid. Filters out duplicate classes with the same class ID.
     *
     * @return Always returns true.
     */
    @Override
    public boolean verify() {
        ArrayList<String> classIds = new ArrayList<>();
        ArrayList<ClassObject> duplicatedClassObjects = new ArrayList<>();
        for (ClassObject classObject : classes) {
            if (classIds.contains(classObject.getId())) {
                duplicatedClassObjects.add(classObject);
            } else {
                classIds.add(classObject.getId());
            }
        }

        for (ClassObject classObject : duplicatedClassObjects) {
            classes.remove(classObject);
        }

        return true;
    }
}
