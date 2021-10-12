package terminus.module;

import terminus.common.TerminusLogger;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;

public class NusModule {


    private ContentManager<Note> noteManager;
    private ContentManager<Link> linkManager;

    public NusModule() {
        noteManager = new ContentManager<>();
        linkManager = new ContentManager<>();
    }


    public <T extends Content> ContentManager<T> getContentManager(Class<T> type) {
        TerminusLogger.info(String.format("Get ContentManager from NusModule with provided class type: %s", type));
        ContentManager<T> result = null;
        if (type == Note.class) {
            result = (ContentManager<T>) this.noteManager;
        } else if (type == Link.class) {
            result = (ContentManager<T>) this.linkManager;
        } else {
            // Fatal error encountered
            TerminusLogger.severe(String.format("Class type provided not found: %s", type));
            assert false;
        }
        TerminusLogger.info("ContentManager found");
        return result;
    }

}
