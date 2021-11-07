package terminus.timetable;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.module.ModuleManager;

class ConflictManagerTest {

    private ModuleManager moduleManager;
    private ConflictManager conflictManager;
    private Link newLink;
    private ArrayList<Link> currentLinks;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        this.newLink = new Link("test conflict", "Saturday", LocalTime.of(9, 00), 3, "https://zoom.us/test");
        this.currentLinks = new ArrayList<Link>();
    }

    @Test
    void execute_getAllLinks_success() {
        conflictManager = new ConflictManager(moduleManager, newLink);

        for (int i = 0; i < 4; i++) {
            currentLinks.add(new Link("test conflict", "Friday", LocalTime.of(7, 00), 2, "https://zoom.us/test"));
            assertNotNull(conflictManager.getAllLinks());
        }
    }

    @Test
    void execute_getConflictingSchedule_success() {
        conflictManager = new ConflictManager(moduleManager, newLink);

        for (int i = 0; i < 4; i++) {
            currentLinks.add(new Link("test conflict", "Saturday", LocalTime.of(8, 00), 2, "https://zoom.us/test"));
            assertNotNull(conflictManager.getConflictingSchedule());
        }
    }
}