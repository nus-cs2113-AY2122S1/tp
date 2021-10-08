package seedu.traveller.database;

import seedu.traveller.mapper.GraphList;
import seedu.traveller.mapper.Vertex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabaseInput {
    private static final int NUMBER_OF_CITIES = 5;
    private static final String FILE_PATH = "flights.txt";
    private static final String SEPARATOR = "\\|";

    public static void readFile() {
        if (hasData()) {
            try {
                readData();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file");
        }
    }

    public static boolean hasData() {
        File data = new File(FILE_PATH);
        return data.exists();
    }

    public static void readData() throws FileNotFoundException {
        File data = new File(FILE_PATH);
        Scanner s = new Scanner(data);
        String input;
        String[] inputWords;
        Vertex v;
        Vertex from;
        Vertex to;
        double weight = 0;
        while (s.hasNext()) {
            for (int i = 0; i < NUMBER_OF_CITIES; i++) {
                input = s.nextLine();
                inputWords = input.split(SEPARATOR);
                if (i == 0) { //Reading 1st line
                    for (int j = 0; j < NUMBER_OF_CITIES; j++) {
                        v = new Vertex(inputWords[j], j + 1);
                        GraphList.addVertex(v);
                    }
                } else { //Reading 2nd line and further
                    for (int k = 0; k < i; k++) {
                        weight = Double.parseDouble(inputWords[k]);
                        from = GraphList.vertexArray.get(k);
                        to = GraphList.vertexArray.get(i);
                        if (weight != 0) {
                            GraphList.createEdge(weight, to, from);
                        }
                    }
                }

            }
        }
    }


}
