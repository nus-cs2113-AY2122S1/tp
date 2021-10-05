package terminus.module;

import java.util.ArrayList;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.content.Note;

public class NusModule {

    private ArrayList<Content> notes;

    private ContentManager contentManager;

    public NusModule() {
        contentManager = new ContentManager();
        notes = new ArrayList<Content>();
    }

    public ContentManager getContentManager() {
        return contentManager;
    }

    public ArrayList<Content> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Content> notes) {
        this.notes = notes;
    }

    public <T> ArrayList<Content> get(T type) {
        ArrayList<Content> result = new ArrayList<>();
        if(type == Note.class){
            result = this.notes;
        }else{
            // Error encountered
        }
        return result;
    }
}
