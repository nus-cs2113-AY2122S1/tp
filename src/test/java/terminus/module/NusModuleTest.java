package terminus.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;

public class NusModuleTest {

    private NusModule module;

    @BeforeEach
    void setUp() {
        this.module = new NusModule();
    }

    @Test
    void getContent_success() throws InvalidArgumentException {
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        ContentManager<Link> linkContentManager = module.getContentManager(Link.class);
        Note note = new Note("test1", "test1");
        Link link = new Link("test1", "test1", LocalTime.now(), "test1");
        noteContentManager.add(note);
        linkContentManager.add(link);
        assertEquals(note.getDisplayInfo(), noteContentManager.getContentData(1));
        assertEquals(link.getDisplayInfo(), linkContentManager.getContentData(1));
    }

    @Test
    void getContent_exceptionThrown() {
        assertThrows(AssertionError.class, () -> module.getContentManager(null));
    }

}
