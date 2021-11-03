package seplanner.parser;

import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;
import seplanner.modules.Module;

public interface ParserError {

   // keep interface in case we decide to implement this instead of ParseError class
   boolean isNumeric(String input);
   boolean isEmptyInput(String input);
   University getUniFromInput(String input);
   boolean isNullUniversity(University university);
   boolean isMissingArguments(String[] argumentSubstrings); //length < 2
   boolean isIndexOutOfBounds(UniversityList universityMasterList, int inputIndex);
   boolean isNullModule(Module module);
   boolean isIndexOutOfBounds(ModuleList moduleMasterList, int inputIndex);
   boolean isDuplicateUniversity(UniversityList universitySelectedList, String uniName);
   boolean isDuplicateModule(ModuleList moduleSelectedList, Module module);
   boolean isInUniversitySelectedList(UniversityList universitySelectedList, University university);
   boolean isNoAvailableMapping(University university, int uniIndex, UniversityList universityMasterList,
                                ModuleList moduleSelectedList);
   boolean isIndexOutOfBounds(int uniIndex, int mapIndex, UniversityList universityMasterList,
                              ModuleList moduleSelectedList);
   boolean isDuplicateMapping(University university, int uniIndex, int mapIndex, UniversityList universityMasterList,
                              ModuleList moduleSelectedList);
}
