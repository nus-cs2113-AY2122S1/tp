package seedu.online;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import seedu.command.flags.SearchFlags;
import seedu.exceptions.FetchException;
import seedu.module.Module;
import seedu.storage.ModStorage;
import seedu.ui.TextUi;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NusMods {

    private static final Logger logger = Logger.getLogger("");

    private static final String MODULE_API = "https://api.nusmods.com/v2/2021-2022/modules/";

    /**
     * Retrieves mod list from NUSMods API then iterates through it and prints all matching mods. Can be interrupted
     * by pressing ENTER.
     *
     * @param searchTerm  searchTerm that has to be contained in the moduleCode.
     * @param searchFlags secondary variables that will be checked to narrow the search.
     * @throws IOException if there is no connection.
     */
    public static void searchModsOnline(String searchTerm, SearchFlags searchFlags) throws IOException {
        SearchThread searchThread = new SearchThread(searchTerm, searchFlags);
        searchThread.start();
        InterruptThread interruptThread = new InterruptThread(searchThread);
        interruptThread.start();
        while (searchThread.isAlive()) {
            //locks code here while thread is running
        }
        interruptThread.interrupt();
    }

    /**
     * Checks if mod matches search term and all flags, and prints all matching mods.
     *
     * @param module      module to check.
     * @param searchTerm  search term to be compared.
     * @param searchFlags flags to check with mod details.
     * @return true if everything matches, false otherwise.
     * @throws IOException if there is no connection.
     */
    public static boolean isMatch(Module module, String searchTerm, SearchFlags searchFlags) throws IOException {
        if (module.meetsPreliminaryConditions(searchTerm, searchFlags)) {
            String moduleCode = module.getModuleCode();
            try {
                module = fetchModOnline(moduleCode);
            } catch (FetchException e) {
                throw new IOException();
            }
            if (module.meetsSecondaryConditions(searchFlags)) {
                TextUi.printModBriefDescription(module);
                return true;
            }
        }
        return false;
    }

    /**
     * Fetches a mod from NUSMods, saves it, then returns it.
     *
     * @param moduleCode code of the module to fetch.
     * @return module that was fetched from NUSMods.
     * @throws FetchException if there is no connection.
     */
    public static Module fetchModOnline(String moduleCode) throws FetchException {
        try {
            InputStream inputStream = getOnlineModInfo(moduleCode);
            ModStorage.saveModInfo(moduleCode, inputStream);
            return ModStorage.loadModInfo(moduleCode);
        } catch (Exception e) {
            throw new FetchException("Unable to fetch module, please ensure that module exists");
        }
    }

    /**
     * Retrieves the mod list from NUSMods and returns it as an Inputstream.
     *
     * @return list of mods as an Inputstream.
     * @throws IOException if there is no connection.
     */
    private static InputStream getOnlineModList() throws IOException {
        String url = "https://api.nusmods.com/v2/2021-2022/moduleInfo.json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con.getInputStream();
    }

    /**
     * Retrives detailed mod information from NUSMods and returns it as an Inputstream.
     *
     * @param moduleCode mod to retrieve.
     * @return detailed mod information as an Inputstream.
     * @throws IOException if there is no connection.
     */
    private static InputStream getOnlineModInfo(String moduleCode) throws IOException {
        String url = MODULE_API + moduleCode + ".json";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        return con.getInputStream();
    }

    /**
     * Fetches all mods from NUSMods and saves all of them into the local storage. Can be interrupted by pressing
     * ENTER.
     *
     * @throws IOException if there is no connection.
     */
    public static void updateSequence() throws IOException {
        UpdateThread updateThread = new UpdateThread();
        updateThread.start();
        InterruptThread interruptThread = new InterruptThread(updateThread);
        interruptThread.start();
        while (updateThread.isAlive()) {
            //locks code here while thread is running
        }
        interruptThread.interrupt();
    }

    /**
     * Attempts to first fetch module info from online API.
     * If fails, fetches module information from local save.
     *
     * @param moduleCode Module to be fetched
     * @return a Module instance representing the relevant information
     */
    public static Module fetchMod(String moduleCode) {
        Module module = null;
        try {
            module = fetchModOnline(moduleCode);
            logger.log(Level.INFO, "Online search done");
        } catch (FetchException e) {
            TextUi.printNoConnectionMessage();
            logger.log(Level.INFO, "Unable to retrieve data from NUSMods, searching offline");
            try {
                module = ModStorage.loadModInfo(moduleCode);
                logger.log(Level.INFO, "Offline search done");
                TextUi.printLocalSearchMessage();
                return module;
            } catch (IOException e2) {
                logger.log(Level.INFO, "Unable to find mod locally.");
            }
        }
        return module;
    }

    /**
     * Class for interrupt thread that will interrupt another thread if prompted by hitting ENTER.
     */
    public static class InterruptThread extends Thread {

        private final Thread thread;

        /**
         * Constructor for Interrupt thread. Takes in a thread to interrupt on pressing enter.
         *
         * @param thread Thread to be interrupted.
         */
        public InterruptThread(Thread thread) {
            this.thread = thread;
        }

        /**
         * Runs the thread checking for user input. On pressing enter, this thread interrupts its assigned thread,
         * then ends. Also ends on being interrupted itself.
         */
        public void run() {
            try {
                while (System.in.available() == 0) {
                    try {
                        sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                //clears the newline from System.in in the main loop
                Scanner s = new Scanner(System.in);
                s.nextLine();
            } catch (IOException e) {
                TextUi.printErrorMessage();
            }
            thread.interrupt();
        }
    }

    /**
     * Class for search thread that runs the search function.
     */
    public static class SearchThread extends Thread {

        private final String searchTerm;
        private final SearchFlags searchFlags;

        /**
         * Constructor for the online search function that takes in a search term and search flags.
         *
         * @param searchTerm search term to search for.
         * @param searchFlags search flags to check for.
         */
        public SearchThread(String searchTerm, SearchFlags searchFlags) {
            this.searchTerm = searchTerm;
            this.searchFlags = searchFlags;
        }

        /**
         * Attempts to run the online search function. Prints an error message if it is unable to reach
         * the NUSMods API.
         */
        public void run() {
            try {
                search(searchTerm, searchFlags);
            } catch (IOException e) {
                TextUi.printNoConnectionMessage();
            }
        }

        /**
         * Retrieves mod list from NUSMods API then iterates through it and prints all matching mods. If interrupted,
         * immediately returns.
         *
         * @param searchTerm  searchTerm that has to be contained in the moduleCode.
         * @param searchFlags secondary variables that will be checked to narrow the search.
         * @throws IOException if there is no connection.
         */
        private static void search(String searchTerm, SearchFlags searchFlags) throws IOException {
            TextUi.printSearchStartMessage();
            InputStream inputStream = getOnlineModList();
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            int count = 0;
            reader.beginArray();
            while (reader.hasNext()) {
                if (currentThread().isInterrupted()) {
                    TextUi.printSearchInterruptMessage();
                    return;
                }
                Module module = new Gson().fromJson(reader, Module.class);
                if (isMatch(module, searchTerm, searchFlags)) {
                    count++;
                }
            }
            reader.endArray();
            TextUi.printModsFound(count);
        }
    }

    /**
     * Class for update thread that runs the update function.
     */
    public static class UpdateThread extends Thread {

        /**
         * Attempts to run the update function. Prints an error message if unable to reach the NUSMods API, and
         * Update interrupt message if cancelled early within the 1000ms sleep window.
         */
        public void run() {
            try {
                update();
            } catch (IOException e) {
                TextUi.printNoConnectionMessage();
            } catch (InterruptedException e) {
                TextUi.printUpdateInterruptMessage();
            }
        }

        /**
         * Updates local data by pulling all data for all the mods from the NUSMods API. If interrupted, immediately
         * returns. Also has a 1000ms delay before starting to display the cancel command for users.
         *
         * @throws IOException if unable to reach NUSmods API.
         * @throws InterruptedException if interrupted in the first 1000ms sleep window.
         */
        private static void update() throws IOException, InterruptedException {
            TextUi.printUpdateStartMessage();
            sleep(1000);
            InputStream inputStream = getOnlineModList();
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
            int count = 0;
            reader.beginArray();
            while (reader.hasNext()) {
                if (currentThread().isInterrupted()) {
                    TextUi.printUpdateInterruptMessage();
                    return;
                }
                Module module = new Gson().fromJson(reader, Module.class);
                String moduleCode = module.getModuleCode();
                try {
                    InputStream modStream = getOnlineModInfo(moduleCode);
                    ModStorage.saveModInfo(moduleCode, modStream);
                    count++;
                    TextUi.printUpdateProgressMessage(count);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Failed to save mod" + moduleCode);
                    TextUi.printErrorMessage();
                }
            }
            reader.endArray();
            TextUi.printUpdateSuccessMessage();
        }
    }
}
