package seedu.duke.storage;

import seedu.duke.commands.calendar.AddLectureCommand;
import seedu.duke.exceptions.calendar.DuplicateTaskException;
import seedu.duke.exceptions.calendar.InvalidDateException;
import seedu.duke.schedule.lecture.Lecture;
import seedu.duke.schedule.lecture.LectureList;
import seedu.duke.schedule.task.Task;
import seedu.duke.schedule.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//@@author swatimahadevan
public class StorageLecture {
    public static final String folderName = "storage/tasksdata/";
    public static final String fileName   = "scheduleLectures.txt";
    public static final String filePath = folderName + fileName;

    public static ArrayList<Lecture> dataToLecture(ArrayList<String> data)
            throws InvalidDateException, DuplicateTaskException {
        ArrayList<Lecture> lectures = new ArrayList<>();
        int i = 0;
        int dataSize = data.size();
        while (i < dataSize) {
            String dataLine = data.get(i);
            String[] lectureArguments = dataLine.split("\\|");
            String moduleName = lectureArguments[0].trim();
            String startDate = lectureArguments[1].trim();
            String endDate = lectureArguments[2].trim();
            AddLectureCommand.checkIfDateValid(startDate);
            AddLectureCommand.checkIfDateValid(endDate);
            lectures.add(new Lecture(moduleName, startDate, endDate));
            i++;
        }
        return lectures;
    }

    public static void writeLectureList(LectureList lectureList) throws IOException {
        ArrayList<Lecture> lectures = lectureList. getLectureList();
        ArrayList<String> data = new ArrayList<>();
        for (Lecture lecture : lectures) {
            data.add(lecture.toSaveFileFormat());
        }
        Storage.writeDataOntoSaveFile(StorageLecture.filePath, data);
    }

    public static LectureList readLectureList()
            throws NullPointerException,
            IOException {
        LectureList lectureList = new LectureList();
        ArrayList<Lecture> lectures;
        try {
            Storage.checkAndAddDirectory(StorageLecture.folderName);
            ArrayList<String> data = Storage.loadDataFromSaveFile(StorageLecture.filePath);
            lectures = StorageLecture.dataToLecture(data);
            for (int i = 0; i < lectures.size(); i++) {
                lectureList.addLecture(lectures.get(i));
            }
            return lectureList;
        } catch (FileNotFoundException | InvalidDateException
                | ArrayIndexOutOfBoundsException | DuplicateTaskException e) {
            File f = new File(StorageLecture.filePath);
        }
        return lectureList;
    }
}