package seedu.duke;

import java.util.ArrayList;

public class Parser {

    public static void parseUserInput(String userInput, ArrayList<Trip> listOfTrips) {
        String[] userInputSplit = userInput.split(" ", 2);

        switch (userInputSplit[0]) {
        case "create":
            String[] newTripInfo = userInputSplit[1].split(" ");
            Trip newTrip = new Trip(newTripInfo);
            listOfTrips.add(newTrip);
            System.out.println("Your trip to " + newTrip.getLocation() + " on "
                    + newTrip.getDateOfTripString() + " has been successfully added!");
            break;
        case "delete":
            try {
                int indexToDelete = Integer.parseInt(userInputSplit[1]);
                Trip tripToRemove = listOfTrips.get(indexToDelete - 1);
                listOfTrips.remove(indexToDelete - 1);
                System.out.println("Your trip to " + tripToRemove.getLocation() + " on "
                        + tripToRemove.getDateOfTripString() + " has been successfully removed");
            } catch (NumberFormatException e) {
                System.out.println("Please check that you have entered your trip number correctly.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, no such trip number exists. Please check your trip number and try again.");
            }
            break;
        default:
            System.out.println("Sorry, we didn't recognize your entry. Please try again, or enter -help "
                    + "to learn more.");
        }
    }

    public static ArrayList<Person> splitPeople(String peopleChained) {

        ArrayList<Person> listOfPeople = new ArrayList<>();
        String[] peopleSplit = peopleChained.split(",");
        for (int i = 0; i < peopleSplit.length; i++) {
            Person person = new Person(peopleSplit[i], (i == 0));
            listOfPeople.add(person);
        }
        return listOfPeople;

    }
}
