package seedu.traveller.worldmap.exceptions;

//@@author gavienwz
public class IllegalFlightFileException extends WorldMapException {
    private String filePath;

    public IllegalFlightFileException(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMessage() {
        return filePath + " has been tampered with. Please re-download " + filePath + ". \n"
                + "Now exiting.";
    }
}
