package seedu.tp.utility;

import seedu.tp.exception.BrowseFailException;

import java.awt.Desktop;
import java.net.URI;

public class ExternalHelper {
    public static void browseUri(URI uri) throws BrowseFailException {
        try {
            Desktop.getDesktop().browse(uri);
        } catch (Exception e) {
            throw new BrowseFailException(e.getMessage());
        }
    }
}
