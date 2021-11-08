package scene.suspect;

import scene.SceneListBuilder;
import scene.clue.Clue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class SuspectListBuilder {
    /**
     * Builds the suspect list from the given file location.
     *
     * @param fileLocation Location of the saved file.
     * @param suspectList Suspect list to be added.
     * @throws FileNotFoundException If the file could not be found.
     */
    public static void suspectListBuilder(String fileLocation, SuspectList suspectList) throws FileNotFoundException {
        InputStream f = SceneListBuilder.class.getResourceAsStream(fileLocation);
        if (f == null) {
            throw new FileNotFoundException();
        }
        Scanner sc = new Scanner(f);

        int numOfSuspect = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfSuspect; i++) {
            String suspectName = sc.nextLine();
            Suspect suspect = new Suspect();
            suspectList.addSuspect(suspectName, suspect);
        }

        int numOfClues = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < numOfClues; i++) {
            int count = 0;
            String suspect = "";
            StringBuilder name = new StringBuilder();
            StringBuilder image = new StringBuilder();
            StringBuilder description = new StringBuilder();
            String phrase = sc.nextLine();
            while (!phrase.equals("**")) {
                if (phrase.equals("*")) {
                    count += 1;
                } else if (count == 0) {
                    suspect = phrase;
                } else if (count == 1) {
                    name.append(phrase);
                } else if (count == 2) {
                    image.append(phrase).append("\n");
                } else if (count == 3) {
                    description.append(phrase).append("\n");
                }
                phrase = sc.nextLine();
            }
            Clue clueToAdd = new Clue(name.toString(), image.toString(), description.toString());
            suspectList.addClueForSuspect(suspect, clueToAdd);
        }
        sc.close();
    }
}
