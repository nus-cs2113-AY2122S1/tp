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
            break;
        case "delete":
            try {
                int indexToDelete = Integer.parseInt(userInputSplit[1]);
                listOfTrips.remove(indexToDelete);
            } catch (NumberFormatException e) {
                //TODO: catch integer exception, do not delete trip
            }
            break;
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
