package terminus.content;

import java.util.ArrayList;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;

public class ContentManager<T extends Content> {

    private ArrayList<T> contents;

    public ContentManager() {
        contents = new ArrayList<>();
    }

    public void setContent(ArrayList<T> contents) {
        this.contents = contents;
    }

    public ArrayList<T> getContents() {
        return contents;
    }

    public int getTotalContents() {
        return contents.size();
    }

    public String listAllContents() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (T n : contents) {
            result.append(String.format("%d. %s\n", i, n.getViewDescription()));
            i++;
        }
        return result.toString();
    }

    public String getContentData(int contentNumber) throws InvalidArgumentException {
        if (isNotValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        return contents.get(contentNumber - 1).getDisplayInfo();
    }

    public String deleteContent(int contentNumber) throws InvalidArgumentException {
        if (isNotValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        String deletedContentName = contents.get(contentNumber - 1).getName();
        contents.remove(contentNumber - 1);
        return deletedContentName;
    }

    public void add(T content) {
        contents.add(content);
    }

    private boolean isNotValidNumber(int number) {
        return number < 1 || number > contents.size();
    }


}
