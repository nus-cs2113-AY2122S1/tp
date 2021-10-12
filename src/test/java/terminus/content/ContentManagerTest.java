package terminus.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.exception.InvalidArgumentException;

public class ContentManagerTest {

    private ContentManager<Note> noteContentManager;
    private ContentManager<Link> linkContentManager;

    @BeforeEach
    void setUp() {
        this.noteContentManager = new ContentManager<>();
        this.linkContentManager = new ContentManager<>();
    }

    @Test
    void addContent_note_success() throws InvalidArgumentException {
        Note note = new Note("test", "test1");
        noteContentManager.add(note);
        assertEquals(note.getDisplayInfo(), noteContentManager.getContentData(1));
    }

    @Test
    void addContent_link_success() throws InvalidArgumentException {
        Link link = new Link("test", "monday", LocalTime.now(), "test.com");
        linkContentManager.add(link);
        assertEquals(link.getDisplayInfo(), linkContentManager.getContentData(1));
    }

    @Test
    void deleteContent_link_success() throws InvalidArgumentException {
        Link link = new Link("test", "monday", LocalTime.now(), "test.com");
        linkContentManager.add(link);
        assertEquals(1, linkContentManager.getTotalContents());
        assertSame(link.getName(), linkContentManager.deleteContent(1));
        assertEquals(0, linkContentManager.getTotalContents());
    }

    @Test
    void deleteContent_note_success() throws InvalidArgumentException {
        Note note = new Note("test", "test1");
        noteContentManager.add(note);
        assertEquals(1, noteContentManager.getTotalContents());
        assertSame(note.getName(), noteContentManager.deleteContent(1));
        assertEquals(0, noteContentManager.getTotalContents());
    }

    @Test
    void deleteContent_exceptionThrown() throws InvalidArgumentException {
        Link link = new Link("test", "monday", LocalTime.now(), "test.com");
        linkContentManager.add(link);
        assertThrows(InvalidArgumentException.class, () -> linkContentManager.deleteContent(-1));
        assertThrows(InvalidArgumentException.class, () -> linkContentManager.deleteContent(0));
        assertThrows(InvalidArgumentException.class, () -> linkContentManager.deleteContent(99));
    }

    @Test
    void getContent_note_success() throws InvalidArgumentException {
        Note note = new Note("test", "test1");
        noteContentManager.add(note);
        assertEquals(note.getDisplayInfo(), noteContentManager.getContentData(1));
    }

    @Test
    void getContent_link_success() throws InvalidArgumentException {
        Link link = new Link("test", "monday", LocalTime.now(), "test.com");
        linkContentManager.add(link);
        assertEquals(link.getDisplayInfo(), linkContentManager.getContentData(1));
    }

    @Test
    void getContent_exceptionThrown() throws InvalidArgumentException {
        Note note = new Note("test", "test1");
        noteContentManager.add(note);
        assertThrows(InvalidArgumentException.class, () -> noteContentManager.getContentData(-1));
        assertThrows(InvalidArgumentException.class, () -> noteContentManager.getContentData(0));
        assertThrows(InvalidArgumentException.class, () -> noteContentManager.getContentData(99));
    }

    @Test
    void getContentSize_success() {
        Note note = new Note("test1", "test1");
        noteContentManager.add(note);
        assertEquals(1, noteContentManager.getTotalContents());
        note = new Note("test2", "test2");
        noteContentManager.add(note);
        assertEquals(2, noteContentManager.getTotalContents());
        note = new Note("test3", "test3");
        noteContentManager.add(note);
        assertEquals(3, noteContentManager.getTotalContents());
    }

    @Test
    void listContent_note_success() {
        Note note1 = new Note("test1", "test1");
        Note note2 = new Note("test2", "test2");
        Note note3 = new Note("test3", "test3");
        noteContentManager.add(note1);
        noteContentManager.add(note2);
        noteContentManager.add(note3);
        String result = noteContentManager.listAllContents();
        assertTrue(result.contains(note1.getViewDescription()));
        assertTrue(result.contains(note2.getViewDescription()));
        assertTrue(result.contains(note3.getViewDescription()));
    }

    @Test
    void listContent_link_success() {
        Link link1 = new Link("test1", "monday", LocalTime.now(), "test.com");
        Link link2 = new Link("test2", "monday", LocalTime.now(), "test.com");
        Link link3 = new Link("test3", "monday", LocalTime.now(), "test.com");
        linkContentManager.add(link1);
        linkContentManager.add(link2);
        linkContentManager.add(link3);
        String result = linkContentManager.listAllContents();
        assertTrue(result.contains(link1.getViewDescription()));
        assertTrue(result.contains(link2.getViewDescription()));
        assertTrue(result.contains(link3.getViewDescription()));
    }

}
