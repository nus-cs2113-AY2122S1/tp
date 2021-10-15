package seedu.typists.content;

import java.util.ArrayList;

/**
 * Content object.
 */
public class Content {
    private ArrayList<String> content;

    public void setContent(String content) {
        this.content = new ArrayList<>(100);
        String content1 = "The greatest glory in living lies not in never falling, but in rising every time we fall.";
        String content2 = "The way to get started is to quit talking and begin doing.";
        this.content.set(0,content1);
        this.content.set(1,content2);
    }


    public String getContent(int index) {
        return content.get(index);
    }
}
