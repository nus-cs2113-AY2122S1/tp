package seedu.traveller.exceptions;

public class InvalidShortestCostException extends TravellerException {
    public InvalidShortestCostException() {
        message = "\tWrong format for Shortest!\n"
                + "\tCorrect formats: \n"
                + "\tshortest-cost /from START /to END\n";
    }
}
