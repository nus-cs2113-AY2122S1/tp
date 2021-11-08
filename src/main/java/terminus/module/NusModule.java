package terminus.module;

import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;
import terminus.content.Question;

/**
 * NusModule class to represent a Module object.
 */
public class NusModule {

    private final transient ContentManager<Note> noteManager;
    private final ContentManager<Link> linkManager;
    private final ContentManager<Question> questionManager;

    /**
     * Creates a NusModule object.
     */
    public NusModule() {
        noteManager = new ContentManager<>();
        linkManager = new ContentManager<>();
        questionManager = new ContentManager<>();
    }

    /**
     * Returns a ContentManager object based on the provided class type.
     *
     * @param type Content class type.
     * @param <T>  Content object type.
     * @return The ContentManager object based on the provided class type.
     */
    public <T extends Content> ContentManager<T> getContentManager(Class<T> type) {
        TerminusLogger.info(String.format("Get ContentManager from NusModule with provided class type: %s", type));
        ContentManager<T> result = null;
        if (type == Note.class) {
            result = (ContentManager<T>) this.noteManager;
        } else if (type == Link.class) {
            result = (ContentManager<T>) this.linkManager;
        } else if (type == Question.class) {
            result = (ContentManager<T>) this.questionManager;
        } else {
            // Fatal error encountered
            TerminusLogger.severe(String.format("Class type provided not found: %s", type));
            assert false;
            return null;
        }
        TerminusLogger.info("ContentManager found");
        return result;
    }
}
