package terminus.content;

import java.util.ArrayList;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;

public class ContentManager {

    private ArrayList<Content> contents;

    public ContentManager() {

    }

    public void setContent(ArrayList<Content> contents) {
        this.contents = contents;
    }

    public void addNote(String name, String data) {
        contents.add(new Note(name, data));
    }

    public void addLink(String description, String day, String startTime, String zoomLink) {
        contents.add(new Link(description, day, startTime, zoomLink));
    }

    public String listAllContents() {
        String result = "";
        int i = 1;
        for (Content n : contents) {
            result += String.format("%d. %s\n", i, n.getViewDescription());
            i++;
        }
        return result;
    }

    public String getContentData(int contentNumber) throws InvalidArgumentException {
        if (!isValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        return contents.get(contentNumber - 1).getDisplayInfo();
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public int getTotalContents() {
        return contents.size();
    }

    public String deleteContent(int contentNumber) throws InvalidArgumentException {
        if (!isValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        String deletedContentName = contents.get(contentNumber - 1).getName();
        contents.remove(contentNumber - 1);
        return deletedContentName;
    }

    private boolean isValidNumber(int number) {
        return !(number < 1 || number > contents.size());
    }
}
