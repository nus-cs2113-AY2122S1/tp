package seedu;

import org.junit.jupiter.api.Test;
import seedu.typists.content.WikiImport;
import seedu.typists.exception.InvalidArticleException;
import seedu.typists.exception.InvalidStringInputException;
import seedu.typists.parser.StringParser;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WikiImportTest {

    // methodBeingTested_inputGiven_expectedOutcome
    // Input, Github, relevant article (731 characters)
    @Test
    void getArticle_Github_expectArticle() throws InvalidArticleException {
        String article = "GitHub";
        WikiImport wiki = new WikiImport();
        String content = wiki.getArticle(article);
        assertEquals(731, content.length());
    }

    // test failure case
    // method being tested, Githob, expect exception
    @Test
    void getArticle_Githob_expectException() {
        String article = "Githob";
        WikiImport wiki = new WikiImport();
        assertThrows(InvalidArticleException.class, () -> wiki.getArticle(article));
    }
}
