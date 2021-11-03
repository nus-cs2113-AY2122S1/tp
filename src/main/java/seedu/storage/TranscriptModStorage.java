//package seedu.storage;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import seedu.module.GradedModule;
//import seedu.module.UngradedModule;
//import seedu.ui.TextUi;
//import seedu.unimods.UniMods;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class TranscriptModStorage {
//    private final File gradedModsFilePath;
//    private final File ungradedModsFilePath;
//    private final String gradedModsPath;
//    private final String ungradedModsPath;
//
//    /**
//     * Initialize TranscriptModStorage.
//     */
//    public TranscriptModStorage(String filePathGraded, String filePathUngraded) {
//        this.gradedModsPath = filePathGraded;
//        this.ungradedModsPath = filePathUngraded;
//        this.gradedModsFilePath = new File(gradedModsPath);
//        try {
//            if (!gradedModsFilePath.exists()) {
//                gradedModsFilePath.getParentFile().mkdirs();
//                gradedModsFilePath.createNewFile();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        this.ungradedModsFilePath = new File(ungradedModsPath);
//        try {
//            if (!ungradedModsFilePath.exists()) {
//                ungradedModsFilePath.getParentFile().mkdirs();
//                ungradedModsFilePath.createNewFile();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void loadModules() {
//        try {
//            FileReader gradedModulesReader = new FileReader(gradedModsFilePath);
//            FileReader ungradedModulesReader = new FileReader(ungradedModsFilePath);
//            TypeToken<ArrayList<GradedModule>> token1 = new TypeToken<ArrayList<GradedModule>>() {};
//            Gson gsonGraded = new Gson();
//            ArrayList<GradedModule> gradedModInTranscript = gsonGraded.fromJson(gradedModulesReader, token1.getType());
//
//            TypeToken<ArrayList<GradedModule>> token2 = new TypeToken<ArrayList<GradedModule>>() {};
//            Gson gsonUngraded = new Gson();
//            ArrayList<GradedModule> ungradedModInTranscript = gsonUngraded.fromJson(ungradedModulesReader, token2.getType());
////            LoadGradedModules(gradedModInTranscript);
////            LoadUngradedModules(ungradedModInTranscript);
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//    }
//
//    /**
//     * Save modules in the storage.
//     */
//    public void save() {
//        try {
//            FileWriter fwg = new FileWriter(gradedModsFilePath);
//            FileWriter fwu = new FileWriter(ungradedModsFilePath);
//
//            Gson gsonForGraded = new Gson();
//            Gson gsonForUngraded = new Gson();
//
//            ArrayList<GradedModule> gradedModules = UniMods.getProfileInUse().getRecord().getGradedModules();
//            gsonForGraded.toJson(gradedModules);
////            gradedModules.stream()
////                    .forEach(System.out::print);
//            ArrayList<UngradedModule> ungradedModules = UniMods.getProfileInUse().getRecord().getUngradedModules();
//            gsonForUngraded.toJson(ungradedModules);
//            fwg.flush();
//            fwg.close();
//
//            fwu.flush();
//            fwu.close();
//        } catch (IOException e) {
//            System.out.println(TextUi.ERROR_FAIL_TO_SAVE);
//        }
//    }
//}
