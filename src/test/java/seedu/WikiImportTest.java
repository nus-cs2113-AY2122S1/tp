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
    // Input, Github, relevant article
    @Test
    void getArticle_Github_expectArticle() throws InvalidArticleException {
        String article = "Github";
        WikiImport wiki = new WikiImport();
        String content = wiki.getArticle(article);
        assertEquals("GitHub, Inc. is a provider of Internet hosting for software development and version control using Git. It offers the distributed version control and source code management (SCM) functionality of Git, plus its own features. It provides access control and several collaboration features such as bug tracking, feature requests, task management, continuous integration and wikis for every project.[4] Headquartered in California, it has been a subsidiary of Microsoft since 2018.", content);
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
