package seedu.duke.command;

import javax.swing.text.DefaultEditorKit;

public class DeleteCommand extends Command {
    private String deleteType;
    private int index;
    private String deleteAllType;

    public DeleteCommand(String deleteType, int index) {
        this(deleteType, index, "");
    }

    public DeleteCommand(String deleteType, int index, String deleteAllType) {
        this.deleteType = deleteType;
        this.index = index;
        this.deleteAllType = deleteAllType;
    }
}
