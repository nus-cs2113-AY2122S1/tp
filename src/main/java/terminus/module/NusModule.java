package terminus.module;

import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;

public class NusModule {


    private ContentManager<Note> noteManager;
    private ContentManager<Link> linkManager;

    public NusModule() {
        noteManager = new ContentManager<Note>();
        linkManager = new ContentManager<Link>();
    }


    public <T extends Content> ContentManager<T> getContentManager(Class<T> type) {
        if (type == Note.class) {
            return (ContentManager<T>) this.noteManager;
        } else if (type == Link.class) {
            return (ContentManager<T>) this.linkManager;
        } else {
            //error encountered
            assert false;
        }
        return null;
    }

}
