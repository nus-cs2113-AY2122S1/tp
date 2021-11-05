package seedu.duke.command.flags;

import java.util.ArrayList;
import java.util.List;

public class LessonFlag extends EventFlag {
    public static final String LINK = "link";

    @Override
    public List<String> getAllEditFlags() {
        List<String> editFlags = new ArrayList<>();
        editFlags.addAll(super.getAllEditFlags());
        editFlags.add(LINK);
        return editFlags;
    }
}
