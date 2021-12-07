package seedu.duke.help;

import seedu.duke.commands.Command;
import seedu.duke.ui.Ui;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//@@author ngnigel99
/**
 * Enables accessing classes on a realtime basis.
 * This is used in conjunction with help command,
 * to enable easy printing of syntax and command messages
 * without string overwrite on each update of command.
 * reference: https://www.baeldung.com/java-find-all-classes-in-package
 */
public class ClassPackageReader {
    private static final String CALENDAR_PACKAGE = "seedu.duke.commands.calendar";
    private static final String FOOD_PACKAGE = "seedu.duke.commands.food";
    private static final String JOURNAL_PACKAGE = "seedu.duke.commands.journal";
    private static final String MODULE_PACKAGE = "seedu.duke.commands.module";
    private static final String ZOOM_PACKAGE = "seedu.duke.commands.zoom";
    private static final String METHOD_NAME = "printClassNameAndSyntax";    //in Command class

    /**
     * The system class loader loads all the classes found in the classpath.
     *
     * @param packageName name of package to access
     * @return set of classes accessed
     */
    public static Set<Class> getClasses(String packageName) {
        InputStream stream = getPackageStream(packageName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return getClassesFromPackage(packageName, reader);
    }

    /**
     * Streams a given package.
     * @param packageName package to be streamed.
     * @return stream package as stream.
     */
    private static InputStream getPackageStream(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        return stream;
    }

    /**
     * Returns a set of classes extracting .class files from a package.
     * @param packageName name of the package to extract from.
     * @param reader buffered reader to read the files.
     * @return set of classes extracted.
     */
    private static Set<Class> getClassesFromPackage(String packageName, BufferedReader reader) {
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    /**
     * Merges sets together.
     * @param a Set to be merged.
     * @param b Set(s) to be merged.
     * @param <T> Generic type of the final set.
     * @return Set some set with a, b, ... merged into.
     */
    public static <T> Set<T> mergeSet(Set<T> a, Set<T>... b) {
        return new HashSet<T>() {
            {
                addAll(a);
                for (Set<T> c : b) {
                    addAll(c);
                }
            }
        };
    }

    /**
     * Loads a class given class name and package name.
     * @param className name of the class to be loaded.
     * @param packageName name of the package in which the class is found.
     * @return class some class to be loaded.
     */
    public static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            Ui.printMessage("Not found");
        }
        return null;
    }


    /**
     * Reads all classes on runtime, filters out commands.
     * From filtered list, prints syntax variable tagged to command,
     * using the printSyntax method in Command class.
     *
     * @throws InstantiationException if method fails to run.
     * @throws IllegalAccessException if method fails to run.
     * @throws InvocationTargetException if method fails to run.
     */
    public static void getCommandsAndPrintSyntax() throws InstantiationException,
                                                            IllegalAccessException,
                                                            InvocationTargetException {
        Set<Class> calendarCommands = getClasses(CALENDAR_PACKAGE);
        Set<Class> foodCommands = getClasses(FOOD_PACKAGE);
        Set<Class> journalCommands = getClasses(JOURNAL_PACKAGE);
        Set<Class> moduleCommands = getClasses(MODULE_PACKAGE);
        Set<Class> zoomCommands = getClasses(ZOOM_PACKAGE);

        Set<Class> allClasses = mergeSet(calendarCommands,foodCommands,
                                        journalCommands, moduleCommands,
                                        zoomCommands);

        List<Class> filteredCommands = filterClassesByCommand(allClasses);
        sortCommandByName(filteredCommands);    //sorts by naming convention
        runSyntaxCommands(filteredCommands);
    }

    /**
     * Runs the overloaded <code>getSyntax</code> method in Commands.
     * Assumptions: List only includes classes that extend Command,
     *              and thus can invoke the getSyntax method,
     *              which prints the syntax of the command to the user.
     * @param filteredCommands list of classes that extend Command.
     * @throws InstantiationException If method fails to invoke.
     * @throws IllegalAccessException If method fails to invoke.
     * @throws InvocationTargetException If method fails to invoke.
     */
    private static void runSyntaxCommands(List<Class> filteredCommands) throws InstantiationException,
                                                                                IllegalAccessException,
                                                                                InvocationTargetException {
        for (Class s : filteredCommands) {
            try {
                Method getSyntax = s.getMethod(METHOD_NAME); //void method
                Object newInstance = s.getDeclaredConstructor().newInstance();
                getSyntax.invoke(newInstance);    //run void method
            } catch (NoSuchMethodException e) {
                Ui.printMessage(s.getSimpleName() + " class missing method, ");
                Ui.printMessage("check spelling");
            }
        }
    }

    /**
     * Differentiates command classes from others.
     * @param allClasses all classes read at runtime.
     * @return filteredCommands list of classes that extend Command.
     */
    private static List<Class> filterClassesByCommand(Set<Class> allClasses) {
        List<Class> filteredCommands = new ArrayList<>();
        for (Class s : allClasses) {
            if (Command.class.isAssignableFrom(s)) {    //filter commands
                filteredCommands.add(s);
            } else {
                allClasses.remove(s);
            }
        }
        return filteredCommands;
    }

    /**
     * Sorts command by name lexicographically.
     * @param filteredCommands list of commands.
     */
    private static void sortCommandByName(List<Class> filteredCommands) {
        Collections.sort(filteredCommands, new Comparator<Class>() {
            @Override
            public int compare(Class h1, Class h2) {
                return h1.getSimpleName().compareTo(h2.getSimpleName());
            }
        });
    }
}
