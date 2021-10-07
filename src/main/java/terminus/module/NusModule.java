package terminus.module;

import java.util.ArrayList;
import terminus.content.Content;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;

public class NusModule {

    private ArrayList<Content> notes;
    private ArrayList<Content> links;

    private ContentManager contentManager;

    public NusModule() {
        contentManager = new ContentManager();
        notes = new ArrayList<Content>();
        links = new ArrayList<Content>();
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

    public ArrayList<Content> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<Content> links) {
        this.links = links;
    }

    public <T> void set(T type, ArrayList<Content> contents) {
        if (type == Note.class) {
            this.notes = contents;
        } else if (type == Link.class) {
            this.links = contents;
        } else {
            //error encountered
        }
    }

    public <T> ArrayList<Content> get(T type) {
        ArrayList<Content> result = new ArrayList<>();
        if (type == Note.class) {
            result = this.notes;
        } else if (type == Link.class) {
            result = this.links;
        } else {
            //error encountered
        }
        return result;
    }

}
