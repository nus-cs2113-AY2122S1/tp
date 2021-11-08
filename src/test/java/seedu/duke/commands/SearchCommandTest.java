package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.common.Status;
import seedu.duke.data.Catalogue;
import seedu.duke.data.Magazine;
import seedu.duke.data.Video;
import seedu.duke.data.Item;
import seedu.duke.data.Book;
import seedu.duke.data.Audio;


import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@@author silinche
class SearchCommandTest {
    SearchCommand searchCommand;
    ArrayList<Item> items;
    Catalogue catalogue;
    Audio audio1;
    Audio audio2;
    Book book1;
    Book book2;
    Magazine magazine1;

    @BeforeEach
    void setUp() {
        audio1 = new Audio("Lover", "0192", Status.AVAILABLE, null, null,  "Taylor Swift", "5:00");
        audio2 = new Audio("Thriller", "4192", Status.RESERVED, null, null, "Michael Jackson", "4:00");
        book1 = new Book("The three body problem", "7234", Status.AVAILABLE, null, null,  "Liu Cixin");
        book2 = new Book("Dune", "3457345", Status.LOANED, null, null, "Frank Herbert");
        magazine1 = new Magazine("Economist", "01230", Status.AVAILABLE, null, null,
                "Some random publisher", "30Oct2021");
        items = new ArrayList<Item>();
        items.add(audio1);
        items.add(audio2);
        items.add(book1);
        items.add(book2);
        catalogue = new Catalogue(items);

    }

    @Test
    void testCheckValidArgs() {
        HashMap<String, String> args = new HashMap<>();
        args.put(null, "search");
        searchCommand = new SearchCommand(args);
        assertFalse(searchCommand.checkValidArgs());
        args.put("c", "Magazine");
        assertTrue(searchCommand.checkValidArgs());
    }

    @Test
    void testCheckAdditionalArgs() {
        HashMap<String, String> args = new HashMap<>();
        args.put(null, "search");
        args.put("c", "Magazine");
        args.put("q", "Something");
        searchCommand = new SearchCommand(args);
        assertTrue(searchCommand.checkAdditionalArgs());
        args.remove("q");
        assertFalse(searchCommand.checkAdditionalArgs());
    }

    @Test
    void testCheckMatches() {
        HashMap<String, String> args = new HashMap<>();
        args.put(null, "search");
        args.put("c", "Magazine");
        searchCommand = new SearchCommand(args);

        assertEquals(0, searchCommand.checkMatches(audio1));
        assertEquals(0, searchCommand.checkMatches(audio2));
        assertEquals(0, searchCommand.checkMatches(book1));
        assertEquals(0, searchCommand.checkMatches(book2));
        assertEquals(1, searchCommand.checkMatches(magazine1));
    }
}