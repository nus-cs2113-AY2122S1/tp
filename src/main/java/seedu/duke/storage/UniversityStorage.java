package seedu.duke.storage;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

public class UniversityStorage {
    private static final Logger logger = Logger.getLogger("UniversityStorageLog");

    public ArrayList<University> readUniversityList(InputStream inputStream,
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
                    parseDouble(attributes[6]), moduleMasterList);
            moduleMappingList.add(new ModuleMapping(local, mapped));
            line = br.readLine();
        }
        if (!curr.equals(" ")) {
            universityList.add(new University(curr, moduleMappingList, ++index));
        }
//        logger.log(Level.INFO, "Completed loading of universities");
        return universityList;
    }

    private String[] extractAttributes(String line) {
        String[] attributes = line.split(",");
        if (attributes.length == 7) {
            return attributes;
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
        return updatedAttributes;
    }
}