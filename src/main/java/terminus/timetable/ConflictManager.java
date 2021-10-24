package terminus.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class ConflictManager {

    private ModuleManager moduleManager;
    private Link newLink;

    public ConflictManager(ModuleManager moduleManager, Link newLink) {
        this.moduleManager = moduleManager;
        this.newLink = newLink;
    }

    /**
     * Retrieve all the Link from all the user Modules.
     *
     * @return An ArrayList of all the user Links.
     */
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

    /**
     * Retrieve all the conflicting Links with the newly added Link.
     *
     * @return A string object of all the conflicting Link details.
     */
    public String getConflictingSchedule() {
        ArrayList<Link> currentLinks = getAllLinks();
        StringBuilder conflictList = new StringBuilder();

        currentLinks.stream()
                .filter(x -> newLink.getDay().equalsIgnoreCase(x.getDay()))
                .filter(x -> newLink.getEndTime().compareTo(x.getStartTime()) > 0)
                .filter(x -> newLink.getStartTime().compareTo(x.getEndTime()) < 0)
                .forEach(x -> {
                    conflictList.append(String.format("%s\n", x.getViewDescription()));
                });
        return conflictList.toString();
    }
}
