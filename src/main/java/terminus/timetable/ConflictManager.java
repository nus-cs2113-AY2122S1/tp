package terminus.timetable;

import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ConflictManager {

    private ModuleManager moduleManager;
    private Link newLink;

    public ConflictManager(ModuleManager moduleManager, Link newLink) {
        this.moduleManager = moduleManager;
        this.newLink = newLink;
    }

    public ArrayList<Link> getAllLinks() {
        ArrayList<Link> currentLinks = new ArrayList<Link>();
        String[] modules = moduleManager.getAllModules();
        Stream<String> stream = Arrays.stream(modules);

        stream.forEach(x -> {
           NusModule module = moduleManager.getModule(x);
            ContentManager<Link> contentManager = module.getContentManager(Link.class);
            assert contentManager != null;
            currentLinks.addAll(contentManager.getContents());
        });

        return currentLinks;
    }


    public String getConflictingSchedule() {
        ArrayList<Link> currentLinks = getAllLinks();
        StringBuilder conflictList = new StringBuilder();

        currentLinks.stream().forEach(x -> {
            boolean hasConflict = newLink.getEndTime().compareTo(x.getStartTime()) > 0 && newLink.getStartTime().compareTo(x.getEndTime()) < 0;
            boolean isSameDay = newLink.getDay().equalsIgnoreCase(x.getDay());
            if (isSameDay && hasConflict) {
                conflictList.append(String.format("%s\n", x.getViewDescription()));
            }
        });
        return conflictList.toString();
    }
}
