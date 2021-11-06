package seedu.traveller.exceptions;

public class InvalidShortestTimeException extends TravellerException {
    public InvalidShortestTimeException() {
        message = "\tWrong format for Shortest!\n"
                + "\tCorrect formats: \n"
                + "\tshortest-time /from START /to END\n";
    }
}
