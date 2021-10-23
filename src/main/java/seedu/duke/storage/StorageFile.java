package seedu.duke.storage;

import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.items.mainlists.EventCatalog;
import seedu.duke.items.mainlists.MemberRoster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StorageFile {

    private static final String DEFAULT_FILE_PATH = "data/slamData.txt";
    private static final String DEFAULT_DIRECTORY = "data";

    public void save(MemberRoster memberRoster, EventCatalog eventCatalog) {

        File saveFile = new File(DEFAULT_FILE_PATH);
        checkFileIsValid(saveFile);
        List<String> encodedMembersList = MemberEncoder.encodeMembersList(memberRoster);
        List<String> encodedEventsList = EventEncoder.encodeEventsList(eventCatalog);
        try {
            writeToFile(saveFile, encodedMembersList, encodedEventsList);
        } catch (IOException e) {
            System.out.println("Error writing to file at path: " + DEFAULT_FILE_PATH);
        }
    }

    private static void checkFileIsValid(File saveFile) {

        try {
            if (!saveFile.exists()) {
                Files.createDirectories(Path.of(DEFAULT_DIRECTORY));
                Files.createFile(Path.of(DEFAULT_FILE_PATH));
            }
        } catch (IOException e) {
            System.out.println("Error with default directory/file_path.");
        }
    }

    private void writeToFile(File saveFile, List<String> encodedMembersList, List<String> encodedEventsList)
            throws IOException {
        FileWriter membersWriter = new FileWriter(saveFile);
        for (String member : encodedMembersList) {
            membersWriter.write(member + "\n");
        }
        membersWriter.close();

        FileWriter eventsWriter = new FileWriter(saveFile, true);
        for (String item : encodedEventsList) {
            eventsWriter.write(item + "\n");
        }
        eventsWriter.close();
    }

    public void load(MemberRoster memberRoster, EventCatalog eventCatalog) {
        File saveFile = new File(DEFAULT_FILE_PATH);
        try {
            List<String> encodedLines = getStringsFromFile(saveFile);

            assert encodedLines.isEmpty() || encodedLines.get(0).startsWith("m")
                    : "First String in list should be a Member";

            for (String line : encodedLines) {
                char classType = line.charAt(0);
                switch (classType) {
                case 'm':
                    memberRoster.add(MemberDecoder.decodeMemberFromString(line));
                    break;
                case 'e':
                    eventCatalog.add(EventDecoder.decodeEventFromString(line));
                    break;
                case 't':
                    Event currEvent = eventCatalog.get(eventCatalog.size() - 1);
                    Task task = TaskDecoder.decodeTaskFromString(line);
                    task.setEvent(currEvent);
                    currEvent.addToTaskList(task);
                    updateMemberTasks(task, memberRoster);
                    break;
                default:
                    throw new DukeException("Seems like you have no data for me to load!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Oooh a new user!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Logger logger = Logger.getLogger("Duke logger");
            logger.log(Level.INFO, "potential file format error", e);
        }
    }

    private void updateMemberTasks(Task task, MemberRoster memberRoster) {
        for (Member member : task.memberList) {
            memberRoster.stream()
                    .filter(m -> m.getName().equals(member.getName()))
                    .forEach(m -> m.addTask(task));
        }
    }

    private List<String> getStringsFromFile(File saveFile) throws FileNotFoundException {
        List<String> encodedItems = new ArrayList<>();
        Scanner myScanner = new Scanner(saveFile);
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();
            encodedItems.add(data);
        }

        return encodedItems;
    }
}
