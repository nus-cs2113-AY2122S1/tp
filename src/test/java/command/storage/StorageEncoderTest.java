package command.storage;

import module.Module;
import module.ModuleList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class StorageEncoderTest {

    private static final String ROOT = System.getProperty("user.dir");
    private static final Path FILE_PATH = Paths.get(ROOT, "data", "data.json");
    private static final Path DIRECTORY_PATH = Paths.get(ROOT, "data");

    @Test
    public void encodeModuleList_normalModuleList_success() {
        ModuleList modules = new ModuleList();
        modules.add(new Module("CS2102"));
        modules.add(new Module("CS2112"));
        modules.add(new Module("CS2132"));
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        assertTrue(Files.exists(FILE_PATH));
        try {
            String fileContent = Files.readString(FILE_PATH);
            assertEquals("{\"moduleList\":[{\"letterGrade\":null,\"moduleName\""
                            + ":\"CS2102\",\"schedule\":null,\"taskList\":{\"taskList\":[],"
                            + "\"taskCount\":0},\"scheduleList\":[],\"credits\":0},{\"letterGrade\""
                            + ":null,\"moduleName\":\"CS2112\",\"schedule\":null,\"taskList\":"
                            + "{\"taskList\":[],\"taskCount\":0},\"scheduleList\":[],\"credits\":0},"
                            + "{\"letterGrade\":null,\"moduleName\":\"CS2132\",\"schedule\":null,\"taskList\""
                            + ":{\"taskList\":[],\"taskCount\":0},\"scheduleList\":[],\"credits\":0}]}",
                    fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeModuleList_emptyModuleList_success() {
        ModuleList modules = new ModuleList();
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        assertTrue(Files.exists(FILE_PATH));
        try {
            String fileContent = Files.readString(FILE_PATH);
            assertEquals("{\"moduleList\":[]}", fileContent);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void encodeModuleList_noFile_success() {
        try {
            if (Files.exists(DIRECTORY_PATH)) {
                Files.delete(FILE_PATH);
                Files.delete(DIRECTORY_PATH);
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
        ModuleList modules = new ModuleList();
        StorageEncoder.encodeAndSaveModuleListToJson(modules);
        assertTrue(Files.exists(FILE_PATH));
    }
}