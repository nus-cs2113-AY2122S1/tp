package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * List of tours.
 */
public class TourList {
    public static final String TOUR_NOT_FOUND_MESSAGE = "ERROR: Tour cannot be found. Please try another tour ID.";

    private final ArrayList<Tour> tours;
    private final ArrayList<String> tourIds;
    private final ArrayList<String> tourNames;
    private final ArrayList<Float> tourPrices;
    private ArrayList<String> iteratedTourIds;
    private int tourCount;

    /**
     * Class constructor for TourList.
     */
    public TourList() {
        tours = new ArrayList<>();
        tourIds = new ArrayList<>();
        tourPrices = new ArrayList<>();
        tourNames = new ArrayList<>();
        tourCount = 0;
    }

    /**
     * Getter for clients.
     *
     * @return ArrayList containing all clients in the database.
     */
    public ArrayList<Tour> getTours() {
        return tours;
    }

    public int getTourCount() {
        return tourCount;
    }

    public Tour getTourByIndex(int index) {
        return tours.get(index);
    }

    /**
     * Getter for tour object in the tour list, corresponding to the tour price given.
     *
     * @param price the given tour price
     * @return the tour object, specified by the tour price
     * @throws TourPlannerException if the tour with the given tour price is not found in the database
     */
    public Tour getTourByPrice(Float price) throws TourPlannerException {
        for (Tour currTour : tours) {
            String currTourId = currTour.getId();
            if (Objects.equals(currTour.getPrice(), price) && !iteratedTourIds.contains(currTourId)) {
                iteratedTourIds.add(currTourId);
                return currTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for tour object in the tour list, corresponding to the tour name given.
     *
     * @param name the given tour name
     * @return the tour object, specified by the tour ID
     * @throws TourPlannerException if the tour with the given tour ID is not found in the database
     */
    public Tour getTourByName(String name) throws TourPlannerException {
        for (int i = 0; i < tourCount; i++) {
            Tour currentTour = tours.get(i);
            if (currentTour.getName().equals(name)) {
                return currentTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for tour object in the tour list, corresponding to the tour ID given.
     *
     * @param id the given tour ID, unique to each tour
     * @return the tour object, specified by the tour ID
     * @throws TourPlannerException if the tour with the given tour ID is not found in the database
     */
    public Tour getTourById(String id) throws TourPlannerException {
        for (int i = 0; i < tourCount; i++) {
            Tour currentTour = tours.get(i);
            if (currentTour.getId().equals(id)) {
                return currentTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for the sorted list of tour prices.
     * Sorts the tour prices by the natural ordering of Float.
     *
     * @return the list of sorted tour prices, by ascending order
     * @see Collections#sort
     */
    public ArrayList<Float> getSortedTourPrices() {
        Collections.sort(tourPrices);
        return tourPrices;
    }

    /**
     * Getter for the sorted list of tour IDs.
     * Sorts the tour IDs by the natural ordering of String, ignoring case sensitivities.
     *
     * @return the list of sorted tour IDs, by natural(alphabetical, numerical) order
     * @see Collections#sort(List, Comparator)
     */
    public ArrayList<String> getSortedTourIds() {
        Collections.sort(tourIds, String.CASE_INSENSITIVE_ORDER);
        return tourIds;
    }

    /**
     * Getter for the sorted list of tour names.
     * Sorts the tour names by the natural ordering of String, ignoring case sensitivities.
     *
     * @return the list of sorted tour names, by natural(alphabetical, numerical) order
     * @see Collections#sort(List, Comparator)
     */
    public ArrayList<String> getSortedTourNames() {
        Collections.sort(tourNames, String.CASE_INSENSITIVE_ORDER);
        return tourNames;
    }

    /**
     * Creates a new temporary array each time the function is called.
     * The tour IDs that have been iterated by Ui in the sort command will be added into TourList's
     * temporary array to prevent duplicates.
     */
    public void initTempArray() {
        iteratedTourIds = new ArrayList<>();
    }

    /**
     * Main method for adding a tour.
     *
     * @param tour the tour to-be-added
     */
    public void add(Tour tour) {
        tours.add(tour);
        tourIds.add(tour.getId());
        tourPrices.add(tour.getPrice());
        tourNames.add(tour.getName());
        tourCount++;
    }

    /**
     * Main method for deleting a tour.
     *
     * @param tour the tour to-be-added
     */
    public void cut(Tour tour) {
        tourIds.remove(tour.getId());
        tourPrices.remove(tour.getPrice());
        tourNames.remove(tour.getName());
        tours.remove(tour);
        tourCount--;
    }
}