package seedu.duke.commons.util;

//@@author richwill28
public class LinkUtil {
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String WWW = "www.";

    /**
     * Format a URL to starts with the HTTPS prefix.
     *
     * @param link input URL
     * @return formatted URL
     */
    public static String formatLink(String link) {
        if (link.startsWith(WWW)) {
            return link.replaceFirst(WWW, HTTPS);
        } else if (link.startsWith(HTTP)) {
            return link.replaceFirst(HTTP, HTTPS);
        } else if (link.startsWith(HTTPS)) {
            return link;
        } else {
            return HTTPS + link;
        }
    }
}
