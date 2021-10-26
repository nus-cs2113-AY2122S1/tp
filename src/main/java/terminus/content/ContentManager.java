package terminus.content;

import java.util.ArrayList;
import terminus.common.Messages;
import terminus.exception.InvalidArgumentException;

/**
 * ContentManager class to manage any Content related object.
 *
 * @param <T> Content object type.
 */
public class ContentManager<T extends Content> {

    private ArrayList<T> contents;

    /**
     * Creates a ContentManager object.
     * It will initialize a new ArrayList for its contents.
     */
    public ContentManager() {
        contents = new ArrayList<>();
    }

    public void setContent(ArrayList<T> contents) {
        this.contents = contents;
    }

    public ArrayList<T> getContents() {
        return contents;
    }

    /**
     * Returns the total size of the ArrayList of type Content.
     *
     * @return The total size of ArrayList of type Content.
     */
    public int getTotalContents() {
        return contents.size();
    }

    /**
     * Returns a string containing a list of all Content information from ArrayList contents.
     *
     * @return String of a list of all Content information in ArrayList contents.
     */
    public String listAllContents() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for (T n : contents) {
            result.append(String.format("%d. %s\n", i, n.getViewDescription()));
            i++;
        }
        return result.toString();
    }

    /**
     * Returns string of all information of a Content object.
     *
     * @param contentNumber Number to identify a element in the ArrayList.
     * @return A String of all information of the specified Content object.
     * @throws InvalidArgumentException when given number is ArrayOutOfBounds.
     */
    public String getContentData(int contentNumber) throws InvalidArgumentException {
        if (isNotValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        return contents.get(contentNumber - 1).getDisplayInfo();
    }

    /**
     * Return deleted Content object name.
     *
     * @param contentNumber Number to identify a element in the ArrayList.
     * @return The string name of the deleted Content object.
     * @throws InvalidArgumentException when given contentNumber is ArrayOurOfBounds.
     */
    public String deleteContent(int contentNumber) throws InvalidArgumentException {
        if (isNotValidNumber(contentNumber)) {
            throw new InvalidArgumentException(Messages.ERROR_MESSAGE_EMPTY_CONTENTS);
        }
        String deletedContentName = contents.get(contentNumber - 1).getName();
        contents.remove(contentNumber - 1);
        return deletedContentName;
    }

    /**
     * Adds a Content object into the ArrayList contents.
     *
     * @param content The Content object to be added into the ArrayList contents.
     */
    public void add(T content) {
        contents.add(content);
    }

    private boolean isNotValidNumber(int number) {
        return number < 1 || number > contents.size();
    }

    /**
     * Replaces the current ArrayList for a new empty ArrayList.
     */
    public void purgeData() {
        this.contents = new ArrayList<>();
    }

    public boolean isDuplicateName(String name) {
        if (contents.size() < 1) {
            return false;
        }
        return contents.stream().anyMatch(x -> x.getName().toLowerCase().equals(name.toLowerCase()));
    }
}
