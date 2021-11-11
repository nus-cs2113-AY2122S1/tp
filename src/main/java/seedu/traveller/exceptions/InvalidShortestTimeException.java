package seedu.traveller.exceptions;

//@@author jach23
public class InvalidShortestTimeException extends TravellerException {
    public InvalidShortestTimeException() {
        message = "\tWrong format for Shortest!\n"
                + "\tCorrect formats: \n"
                + "\tshortest-time /from START /to END\n";
    }
}
