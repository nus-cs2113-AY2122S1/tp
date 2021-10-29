package seedu.duke.storage;

import seedu.duke.constants.Constants;
import seedu.duke.modules.Module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

public class ModuleStorage {
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    public ArrayList<Module> readModuleList(InputStream inputStream) throws IOException {
        ArrayList<Module> moduleList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        int index = 0;
        while (line != null) {
            String[] attributes = extractAttributes(line);
            assert parseDouble(attributes[2]) > 0 : "Local module credits should be greater than 0";
            moduleList.add(new Module(attributes[0], attributes[1], parseDouble(attributes[2]), ++index));
            line = br.readLine();
        }
        logger.log(Level.INFO, "Completed loading of modules");
        return moduleList;
    }

    private String[] extractAttributes(String line) {
        String[] attributes = line.split(",");
        if (attributes.length == 3) {
            return attributes;
        }
        String[] updatedAttributes = new String[3];
        int i = 0;
        int j = 0;
        boolean flag = false;
        while (j < 3) {
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