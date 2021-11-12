package seplanner.parser;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@@ author MAZJ124

/**
 * Conditions to be checked by the parser to throw exceptions.
 */
public class ParseCondition {

    /**
     * Checks whether the input from the user is only made of numbers.
     * @param input Input from user
     * @return True if the input is completely numeric, false otherwise
     */
    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the input from the user is only made of alphabets.
     * @param input Input from user
     * @return True if the input is fully alphabetic, false otherwise
     */
    public static boolean isText(String input) {
        String regex = ".*[a-zA-Z].*";  // regex to check if string contains any letters
        Pattern pattern = Pattern.compile(regex);  // compiles the regex
        Matcher matcherText = pattern.matcher(input);
        return matcherText.matches();
    }

    /**
     * Checks whether input from the user is empty.
     * @param input Input from user
     * @return True if the input is empty, false otherwise
     */
    public static boolean isEmptyInput(String input) {
        return input.length() == 0;
    }

    /**
     * Checks whether the user input is missing required arguments or flags.
     * @param argumentSubstrings The list formed up by the words from the user input
     * @return True if there is missing argument, false otherwise
     */
    public static boolean isMissingArguments(String[] argumentSubstrings) {
        return argumentSubstrings.length < 2;
    }

    /**
     * Checks whether the university is a null object.
     * @param university The university object to be checked
     * @return True if the object given is null, false otherwise
     */
    public static boolean isNullUniversity(University university) {
        return university == null;
    }

    /**
     * Checks whether the module is a null object.
     * @param module The module object to be checked
     * @return True if the object given is null, false otherwise
     */
    public static boolean isNullModule(Module module) {
        return module == null;
    }

    /**
     * Checks whether module with the given module code exists.
     * @param moduleMasterList The module master list
     * @param moduleCode Module code of the module to be checked
     * @return True if the module is valid and exists in the master list, false otherwise
     */
    public static Module isValidModule(ModuleList moduleMasterList,String moduleCode) {
        return moduleMasterList.getModule(moduleCode);
    }

    /**
     * Checks whether the index of university from user is valid.
     * @param uniIndex Index from user
     * @param universityMasterList The university master list
     * @return True if the index is out of bounds, false otherwise
     */
    public static boolean isIndexOutOfBounds(int uniIndex, UniversityList universityMasterList) {
        boolean isExceedUpperBound = uniIndex > universityMasterList.getSize();
        boolean isExceedLowerBound = uniIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    /**
     * Checks whether the index of module from user is valid.
     * @param modIndex Index from user
     * @param moduleMasterList The module master list
     * @return True if the index is out of bounds, false otherwise
     */
    public static boolean isIndexOutOfBounds(int modIndex, ModuleList moduleMasterList) {
        boolean isExceedUpperBound = modIndex > moduleMasterList.getSize();
        boolean isExceedLowerBound = modIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    /**
     * Checks whether the index of mapping to add from user for a specific university is valid.
     * @param uniIndex Index of university from user
     * @param mapIndex Index of mapping from user
     * @param universityMasterList The university master list
     * @param moduleSelectedList The selected module list
     * @return True if the index is out of bounds, false otherwise
     */
    public static boolean isAddedMappingIndexOutOfBounds(int uniIndex, int mapIndex,
                          UniversityList universityMasterList, ModuleList moduleSelectedList) {
        University uni = universityMasterList.get(uniIndex - 1);
        int mapSize = uni.getSelectedMappingListSize(moduleSelectedList);
        boolean isExceedUpperBound = mapIndex > mapSize;
        boolean isExceedLowerBound = mapIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    /**
     * Checks whether the index of mapping to remove from user for a specific university is valid.
     * @param uniIndex Index of university from user
     * @param mapIndex Index of mapping from user
     * @param universityMasterList The university master list
     * @param universitySelectedList The selected university list
     * @return True if the index is out of bounds, false otherwise
     */
    public static boolean isRemovedMappingIndexOutOfBounds(int uniIndex, int mapIndex,
                          UniversityList universityMasterList, UniversityList universitySelectedList) {
        University uniMasterObject = universityMasterList.get(uniIndex - 1);
        University uni = universitySelectedList.getUniversity(uniMasterObject.getName());
        int mapSize = uni.getList().size();
        boolean isExceedUpperBound = mapIndex > mapSize;
        boolean isExceedLowerBound = mapIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    /**
     * Checks whether university with the given name exists.
     * @param universityMasterList The university master list
     * @param uniName Name of the university
     * @return True if university with given name exists, false otherwise
     */
    public static boolean isValidUniversity(UniversityList universityMasterList, String uniName) {
        return universityMasterList.isExistUniversity(uniName);
    }

    /**
     * Checks whether university with given name has already been added to the selected list.
     * @param universitySelectedList The selected university list
     * @param uniName Name of the university
     * @return True if the university has already been added, false otherwise
     */
    public static boolean isDuplicateUniversity(UniversityList universitySelectedList, String uniName) {
        return universitySelectedList.isExistUniversity(uniName);
    }

    /**
     * Checks whether module has already been added in the selected list.
     * @param moduleSelectedList The selected module list
     * @param module The module object
     * @return True if the module has been added, false otherwise
     */
    public static boolean isDuplicateModule(ModuleList moduleSelectedList, Module module) {
        String moduleCode = module.getModuleCode();
        return moduleSelectedList.isModuleExist(moduleCode);
    }

    /**
     * Checks whether the mapping from user has already been added for the university.
     * @param university The university object from the selected list
     * @param uniIndex Index of the university
     * @param mapIndex Index of the mapping
     * @param universityMasterList The university master list
     * @param moduleSelectedList The selected module list
     * @return True if the mapping has already been added, false otherwise
     */
    public static boolean isDuplicateMapping(University university, int uniIndex, int mapIndex,
                                             UniversityList universityMasterList, ModuleList moduleSelectedList) {
        University uniMasterObject = universityMasterList.get(uniIndex - 1);
        ModuleMapping map = uniMasterObject.getSelectedMappings(moduleSelectedList).get(mapIndex - 1);
        return university.isExistMapping(map);
    }

    /**
     * Checks whether university has been added to the selected list.
     * @param uniIndex Index of university
     * @param universitySelectedList The selected university list
     * @param universityMasterList The university master list
     * @return True if the university has already been added, false otherwise
     */
    public static boolean isInSelectedUniList(int uniIndex, UniversityList universitySelectedList,
                                              UniversityList universityMasterList) {
        String uniName = universityMasterList.get(uniIndex - 1).getName();
        return universitySelectedList.isExistUniversity(uniName);
    }

    /**
     * Return the corresponding object of a university in the selected university list.
     * @param uniIndex Index of university
     * @param universitySelectedList The selected university list
     * @param universityMasterList The university master list
     * @return The university object from the selected list corresponding to the master list index given
     */
    public static University getSelectedUniObject(int uniIndex, UniversityList universitySelectedList,
                                                  UniversityList universityMasterList) {
        String uniName = universityMasterList.get(uniIndex - 1).getName();
        return universitySelectedList.getUniversity(uniName);
    }

    /**
     * Checks whether there is no mapping available for a university based on the current selected modules.
     * @param uni University to be checked
     * @param moduleSelectedList The selected module list
     * @return True if there is no mapping available, false otherwise
     */
    public static boolean isNoPotentialMapping(University uni, ModuleList moduleSelectedList) {
        int mapSize = uni.getSelectedMappingListSize(moduleSelectedList);
        return mapSize == 0;
    }

    /**
     * Checks whether the university in selected list has any assigned mappings.
     * @param uni University object to be checked
     * @param universitySelectedList The selected university list
     * @return True if there is no mappings assigned, false otherwise 
     */
    public static boolean isNoSelectedMapping(University uni, UniversityList universitySelectedList) {
        String uniName = uni.getName();
        University uniSelectedObject = universitySelectedList.getUniversity(uniName);
        return uniSelectedObject.getList().size() == 0;
    }

}
