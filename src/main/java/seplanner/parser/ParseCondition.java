package seplanner.parser;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

public class ParseCondition {

    public static boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmptyInput(String input) {
        return input.length() == 0;
    }

    public static boolean isMissingArguments(String[] argumentSubstrings) {
        return argumentSubstrings.length < 2;
    }

    public static boolean isNullUniversity(University university) {
        return university == null;
    }

    public static boolean isNullModule(Module module) {
        return module == null;
    }

    public static boolean isIndexOutOfBounds(int uniIndex, UniversityList universityMasterList) {
        boolean isExceedUpperBound = uniIndex > universityMasterList.getSize();
        boolean isExceedLowerBound = uniIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    public static boolean isValidUniversity(UniversityList universityMasterList, String uniName) {
        return universityMasterList.isExistUniversity(uniName);
    }

    public static boolean isDuplicateUniversity(UniversityList universitySelectedList, String uniName) {
        return universitySelectedList.isExistUniversity(uniName);
    }

    public static boolean isIndexOutOfBounds(int modIndex, ModuleList moduleMasterList) {
        boolean isExceedUpperBound = modIndex > moduleMasterList.getSize();
        boolean isExceedLowerBound = modIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    public static boolean isDuplicateModule(ModuleList moduleSelectedList, Module module) {
        String moduleCode = module.getModuleCode();
        return moduleSelectedList.isModuleExist(moduleCode);
    }

    public static boolean isMissingAvailableMapping(int uniIndex, UniversityList universityMasterList,
                                                    ModuleList moduleSelectedList) {
        University uni = universityMasterList.get(uniIndex - 1);
        int mapSize = uni.getSelectedMappingListSize(moduleSelectedList);
        return mapSize == 0;
    }

    public static boolean isIndexOutOfBounds(int uniIndex, int mapIndex, UniversityList universityMasterList,
                                             ModuleList moduleSelectedList) {
        University uni = universityMasterList.get(uniIndex - 1);
        int mapSize = uni.getSelectedMappingListSize(moduleSelectedList);
        boolean isExceedUpperBound = mapIndex > mapSize;
        boolean isExceedLowerBound = mapIndex <= 0;
        return isExceedUpperBound || isExceedLowerBound;
    }

    // university here refers to the uni object in the selected uni list
    public static boolean isDuplicateMapping(University university, int uniIndex, int mapIndex,
                                             UniversityList universityMasterList, ModuleList moduleSelectedList) {
        University uniMasterObject = universityMasterList.get(uniIndex - 1);
        ModuleMapping map = uniMasterObject.getSelectedMappings(moduleSelectedList).get(mapIndex - 1);
        return university.isExistMapping(map);
    }

    public static boolean isInSelectedUniList(int uniIndex, UniversityList universitySelectedList,
                                              UniversityList universityMasterList) {
        String uniName = universityMasterList.get(uniIndex - 1).getName();
        return universitySelectedList.isExistUniversity(uniName);
    }

    public static University getSelectedUniObject(int uniIndex, UniversityList universitySelectedList,
                                                  UniversityList universityMasterList) {
        String uniName = universityMasterList.get(uniIndex - 1).getName();
        return universitySelectedList.getUniversity(uniName);
    }

}
