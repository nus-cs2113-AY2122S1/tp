package seplanner.storage;

import java.io.File;
import java.io.IOException;

public class UserStorage {
    protected File loadFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}
