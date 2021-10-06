package seedu.duke.storage;

import seedu.duke.Classes.Module;
import seedu.duke.Classes.ModuleMapping;
import seedu.duke.Classes.University;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Storage {
    private static final String UNIVERSITY_FILEPATH = "data/University.csv";

    public ArrayList<University> load() throws IOException {
        return readUniversitiesFromCSV();
    }

    private ArrayList<University> readUniversitiesFromCSV() throws IOException {
        ArrayList<University> universityList = new ArrayList<>();
        ArrayList<ModuleMapping> moduleMappingList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(UNIVERSITY_FILEPATH));
        String line = br.readLine();
        String curr = " ";
        while (line != null) {
            String[] attributes = line.split(",");
            if (curr.equals(" ")) {
                curr = attributes[0];
            } else if (!curr.equals(attributes[0])) {
                universityList.add(new University(curr, moduleMappingList));
                curr = attributes[0];
                moduleMappingList.clear();
            }
            Module local = new Module(attributes[1], attributes[2], parseInt(attributes[3]));
            Module mapped = new Module(attributes[4], attributes[5], parseInt(attributes[6]));
            moduleMappingList.add(new ModuleMapping(local, mapped));
            line = br.readLine();
        }
        return universityList;
    }
}