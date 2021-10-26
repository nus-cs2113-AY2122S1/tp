package seedu.traveller.exceptions;

//@@author Jach23
public class InvalidShortestFormatException extends TravellerException {
    public InvalidShortestFormatException() {
        message = "\tWrong format for Shortest!\n"
                + "\tCorrect formats: \n"
                + "\tshortest-dist /from START /to END\n"
                + "\tOR \n"
                + "\tshortest-cost /from START /to END\n";
    }
}
