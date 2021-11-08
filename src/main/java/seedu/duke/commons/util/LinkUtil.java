package seedu.duke.commons.util;

import java.io.IOException;

//@@author richwill28
public class LinkUtil {
    private static final String LINUX_LAUNCH_COMMAND = "xdg-open ";
    private static final String MAC_LAUNCH_COMMAND = "open ";
    private static final String PYTHON_LAUNCH_COMMAND = "python -m webbrowser ";
    private static final String PYTHON3_LAUNCH_COMMAND = "python3 -m webbrowser ";
    private static final String WINDOWS_LAUNCH_COMMAND = "rundll32 url.dll,FileProtocolHandler ";

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

    public static void launchUrlOnLinux(Runtime rt, String url) throws IOException {
        try {
            rt.exec(LINUX_LAUNCH_COMMAND + url);
        } catch (IOException e) {
            // Ignore and fallback to the next option
        }

        // Fallbacks if the user is using WSL
        try {
            rt.exec(WINDOWS_LAUNCH_COMMAND + url);
        } catch (IOException e) {
            // Ignore and fallback to the next option
        }

        try {
            rt.exec(PYTHON_LAUNCH_COMMAND + url);
        } catch (IOException e) {
            // Ignore and fallback to the next option
        }

        // The last fallback option
        rt.exec(PYTHON3_LAUNCH_COMMAND + url);
    }

    public static void launchUrlOnMac(Runtime rt, String url) throws IOException {
        rt.exec(MAC_LAUNCH_COMMAND + url);
    }

    public static void launchUrlOnWindows(Runtime rt, String url) throws IOException {
        rt.exec(WINDOWS_LAUNCH_COMMAND + url);
    }
}
