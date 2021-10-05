package terminus.content;

import terminus.exception.InvalidArgumentException;

import java.util.ArrayList;

public class LinkManager {

    final private static String DATA_SEPARATOR = "";

    private ArrayList<Link> schedules;

    public LinkManager() {

    }

    public void setSchedules(ArrayList<Link> schedules) {
        this.schedules = schedules;
    }

    public void addLink(String description, String day, String startTime, String zoomLink) {
        schedules.add(new Link(description, day, startTime, zoomLink));
    }

    public String listAllLinks() {
        String result = "";
        int i = 1;
        for (Link l : schedules) {
            result += String.format("%d. %s, %s, %s, %s\n", i, l.getDescription(),
                    l.getDay(), l.getStartTime(), l.getZoomLink());
            i++;
        }
        return result;
    }

    public String getLinkData(int contentNumber) throws InvalidArgumentException {
        if (!isValidNumber(contentNumber)) {
            throw new InvalidArgumentException("Error: content not found.");
        }
        return schedules.get(contentNumber - 1).getDisplayInfo();
    }

    public ArrayList<Link> getSchedules() {
        return schedules;
    }

    public int getTotalLinks() {
        return schedules.size();
    }

    public String deleteLink(int contentNumber) throws InvalidArgumentException {
        if (!isValidNumber(contentNumber)) {
            throw new InvalidArgumentException("Error: content not found.");
        }
        String deletedLinkDescription = schedules.get(contentNumber - 1).getDescription() + ", ";
        deletedLinkDescription += schedules.get(contentNumber - 1).getDay() + ", ";
        deletedLinkDescription += schedules.get(contentNumber - 1).getStartTime() + ", ";
        deletedLinkDescription += schedules.get(contentNumber - 1).getZoomLink();
        schedules.remove(contentNumber - 1);
        return deletedLinkDescription;
    }

    private boolean isValidNumber(int number) {
        return !(number < 1 || number > schedules.size());
    }
}
