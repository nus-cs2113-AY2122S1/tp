package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.universities.University;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

//@@author madhanse
/**
 * Handles file reading operation for the university master list stored in the CSV file.
 */
public class UniversityStorage {
    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    /**
     * Reads the CSV file containing the module master list and adds them into the
     * array list.
     * @param inputStream For reading the CSV file stored in the resources root
     * @param moduleMasterList Module Master List
     * @return University Master List
     * @throws IOException If there is a problem accessing the file
     */
    public ArrayList<University> readFile(InputStream inputStream,
                                          ModuleList moduleMasterList) throws IOException {
        ArrayList<University> universityList = new ArrayList<>();
        ArrayList<ModuleMapping> moduleMappingList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        String curr = " ";
        int index = 0;
        while (line != null) {
            String[] attributes = extractAttributes(line);
            if (curr.equals(" ")) {
                curr = attributes[0];
            } else if (!curr.equals(attributes[0])) {
                universityList.add(new University(curr, moduleMappingList, ++index));
                curr = attributes[0];
                moduleMappingList = new ArrayList<>();
            }
            assert parseDouble(attributes[3]) > 0 : "Local module credits should be positive";
            Module local = new Module(attributes[1], attributes[2],
                    parseDouble(attributes[3]), moduleMasterList);
            assert parseDouble(attributes[6]) > 0 : "Mapped module credits should be positive";
            Module mapped = new Module(attributes[4], attributes[5],
                    parseDouble(attributes[6]), 0);
            moduleMappingList.add(new ModuleMapping(local, mapped));
            line = br.readLine();
        }
        if (!curr.equals(" ")) {
            universityList.add(new University(curr, moduleMappingList, ++index));
        }
        logger.log(Level.INFO, "Completed loading of universities");
        return universityList;
    }

    /**
     * Converts the line read by BufferedReader into attributes.
     * @param line Line read by BufferedReader
     * @return String array containing the attributes
     */
    private String[] extractAttributes(String line) {
        String[] attributes = line.split(",");
        if (attributes.length == 7) {
            return editMappedModuleCode(attributes);
        }
        String[] updatedAttributes = new String[7];
        int i = 0;
        int j = 0;
        boolean flag = false;
        while (j < 7) {
            if (!flag) {
                updatedAttributes[j] = attributes[i];
                j++;
            } else {
                updatedAttributes[j - 1] += ("," + attributes[i]);
            }
            if (attributes[i].startsWith("\"") && !flag) {
                flag = true;
                updatedAttributes[j - 1] = updatedAttributes[j - 1].substring(1);
            }
            if (attributes[i].endsWith("\"") && flag) {
                flag = false;
                int length = updatedAttributes[j - 1].length();
                updatedAttributes[j - 1] = updatedAttributes[j - 1].substring(0, length - 1);
            }
            i++;
        }
        return editMappedModuleCode(updatedAttributes);
    }

    /**
     * Removes the double commas for the mapped modules which needs to be fixed.
     * This is for the mapped modules which changes to a simplified integer at any time.
     * @param attributes String array containing the attributes
     * @return Updated String array containing the attributes
     */
    private String[] editMappedModuleCode(String[] attributes) {
        if (attributes[4].startsWith("\"") && attributes[4].endsWith("\"")) {
            int length = attributes[4].length();
            attributes[4] = attributes[4].substring(1, length - 1);
        }
        return attributes;
    }
}