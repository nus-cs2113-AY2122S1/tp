package seedu.traveller.exceptions;

public class InvalidShortestDistException extends TravellerException {
    public InvalidShortestDistException() {
        message = "\tWrong format for Shortest!\n"
                + "\tCorrect formats: \n"
                + "\tshortest-dist /from START /to END\n";
    }
}
