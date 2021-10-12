package seedu.typists.content;

import org.fastily.jwiki.core.Wiki;

/**
 * Imports the first paragraph of a text from Wikipedia.
 */
public class WikiImport {
    public String getArticle(String article) {
        Wiki wiki = new Wiki.Builder().build();
        return wiki.getTextExtract(article);
    }
}
