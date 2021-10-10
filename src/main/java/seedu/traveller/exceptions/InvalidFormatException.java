package seedu.traveller.exceptions;

public class InvalidFormatException extends TravellerException {
    private String command;

    public InvalidFormatException(String command) {
        switch (command) {
<<<<<<< HEAD
            case "new":
                message = "\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END";
                break;
            case "edit":
                message = "\tWrong format for Edit!\n\tCorrect format: edit TRIP_NAME START END";
                break;
            default:
                message = "\tWrong format!";
                break;
        }
    }
}
=======
        case "new":
            message = "\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END";
            break;
        case "edit":
            message = "\tWrong format for Edit!\n\tCorrect format: edit TRIP_NAME START END";
            break;
        default:
            message = "\tWrong format!";
            break;
        }
    }
}
>>>>>>> 5b8b9f08baa07ae4f375579985de11036ae5a1e8
