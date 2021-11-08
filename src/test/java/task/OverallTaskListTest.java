package task;

import exceptions.NoCapExceptions;
import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;

class OverallTaskListTest extends TaskListTest {

    @Test
    void addModuleList_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 21/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/08/2022 1600");
        modules.get(2).addTask("sleep /by 21/08/2022 1600");
        modules.get(0).addGradableTask("assignment /by 21/08/2022 /w 50");
        OverallTaskList taskList = new OverallTaskList(modules);
        String expected = "[CS2102][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator()
                + "[CS2102][G][ ] assignment by: 21 Aug 2022 12:00 AM [Weightage: 50%]" + System.lineSeparator()
                + "[CS2112][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator()
                + "[CS2132][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator();
        assertEquals(expected, taskList.toString());
    }

    @Test
    void addModuleList_emptyList_success() {
        ModuleList modules = new ModuleList();
        OverallTaskList taskList = new OverallTaskList(modules);
        String expected = "";
        assertEquals(expected, taskList.toString());
    }

    @Test
    void addModuleList_taskListOnly_success() {
        ModuleList modules = new ModuleList();
        OverallTaskList taskList = new OverallTaskList(modules);
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("assignment /by 21/08/2022 1600");
        String expected = "";
        assertEquals(expected, taskList.toString());
    }

    @Test
    void addModuleList_gradableListOnly_success() {
        ModuleList modules = new ModuleList();
        OverallTaskList taskList = new OverallTaskList(modules);
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addGradableTask("assignment /by 21/08/2022 /w 50");
        String expected = "";
        assertEquals(expected, taskList.toString());
    }

    @Test
    void printModuleList_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 21/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/08/2022 1600");
        modules.get(2).addTask("sleep /by 21/08/2022 1600");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printAllTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "All tasks: " + System.lineSeparator()
                        + "1. [CS2102][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "2. [CS2112][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "3. [CS2132][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printModuleList_emptyList_success() {
        ModuleList modules = new ModuleList();
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printAllTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "All tasks: " + System.lineSeparator()
                        + "You have no tasks" + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void sortModuleListByDate_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/08/2022 1600");
        modules.get(2).addTask("sleep /by 20/08/2022 1600");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.sortByDateAndPrint();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Tasks sorted by date: " + System.lineSeparator()
                        + "1. [CS2132][ ][ ] sleep by: 20 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "2. [CS2112][ ][ ] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "3. [CS2102][ ][ ] sleep by: 23 Aug 2022 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void sortModuleListByStatus_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/08/2022 1600");
        modules.get(2).addTask("sleep /by 20/08/2022 1600");
        modules.get(1).getTaskList().taskList.get(0).markDone();
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.sortByStatusAndPrint();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Tasks sorted by status: " + System.lineSeparator()
                        + "1. [CS2102][ ][ ] sleep by: 23 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "2. [CS2132][ ][ ] sleep by: 20 Aug 2022 04:00 PM " + System.lineSeparator()
                        + "3. [CS2112][ ][X] sleep by: 21 Aug 2022 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printWeeklyTask_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/10/2021 1600");
        modules.get(2).addTask("sleep /by 20/11/2021 1600");
        modules.get(2).addTask("sleep /by 21/11/2021 1600");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printWeeklyTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Weekly tasks: " + System.lineSeparator()
                        + "1. [CS2112][ ][LATE][ ] sleep by: 21 Oct 2021 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printMonthlyTask_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/08/2022 1600");
        modules.get(1).addTask("sleep /by 21/10/2021 1600");
        modules.get(2).addTask("sleep /by 12/11/2021 1600");
        modules.get(2).addTask("sleep /by 10/11/2021 1600");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printMonthlyTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Monthly tasks: " + System.lineSeparator()
                        + "1. [CS2112][ ][LATE][ ] sleep by: 21 Oct 2021 04:00 PM " + System.lineSeparator()
                        + "2. [CS2132][ ][ ] sleep by: 10 Nov 2021 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printYearlyTask_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2132"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/12/2022 1600");
        modules.get(1).addTask("sleep /by 21/10/2021 1600");
        modules.get(2).addTask("sleep /by 20/11/2021 1600");
        modules.get(2).addTask("sleep /by 21/11/2021 1600");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printYearlyTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Yearly tasks: " + System.lineSeparator()
                        + "1. [CS2112][ ][LATE][ ] sleep by: 21 Oct 2021 04:00 PM " + System.lineSeparator()
                        + "2. [CS2132][ ][ ] sleep by: 21 Nov 2021 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printNormalTask_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/12/2022 1600");
        modules.get(1).addTask("sleep /by 21/10/2021 1600");
        modules.get(0).addGradableTask("assignment /by 23/12/2022 /w 20");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printNormalTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Non-gradable tasks: " + System.lineSeparator()
                        + "1. [CS2102][ ][ ] sleep by: 23 Dec 2022 04:00 PM " + System.lineSeparator()
                        + "2. [CS2112][ ][LATE][ ] sleep by: 21 Oct 2021 04:00 PM " + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }

    @Test
    void printGradableTask_normalList_success() {
        ModuleList modules = new ModuleList();
        try {
            modules.add(new Module("CS2102"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        try {
            modules.add(new Module("CS2112"));
        } catch (NoCapExceptions e) {
            e.printStackTrace();
        }
        modules.get(0).addTask("sleep /by 23/12/2022 1600");
        modules.get(1).addTask("sleep /by 21/10/2021 1600");
        modules.get(0).addGradableTask("assignment /by 23/12/2022 /w 20");
        OverallTaskList taskList = new OverallTaskList(modules);
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        System.setOut(save);
        taskList.printGradableTasks();
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList(
                "Gradable tasks: " + System.lineSeparator()
                        + "1. [CS2102][G][ ] assignment by: 23 Dec 2022 12:00 AM "
                        + "[Weightage: 20%]" + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }
}