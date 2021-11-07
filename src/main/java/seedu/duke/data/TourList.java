package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * List of tours.
 */
public class TourList {
    private static final String TOUR_NOT_FOUND_MESSAGE = "Tour cannot be found. Please try another tour ID.";

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

    public ArrayList<Tour> getTours() {
        return tours;
    }

    public ArrayList<Float> getSortedTourPrices() {
        Collections.sort(tourPrices);
        return tourPrices;
    }

    public ArrayList<String> getSortedTourIds() {
        Collections.sort(tourIds, String.CASE_INSENSITIVE_ORDER);
        return tourIds;
    }

    public ArrayList<String> getSortedTourNames() {
        Collections.sort(tourNames, String.CASE_INSENSITIVE_ORDER);
        return tourNames;
    }

    /**
     * Getter for number of tours in the tour list.
     *
     * @return the number of tours in tour list.
     */
    public int getTourCount() {
        return tourCount;
    }

    /**
     * Getter for a specific tour based on its index in the list.
     *
     * @param index the index which the tour is found at.
     * @return a specific tour with a certain index.
     */
    public Tour getTourByIndex(int index) {
        return tours.get(index);
    }

    public void initTempArray() {
        iteratedTourIds = new ArrayList<String>();
    }

    /**
     * Getter for a specific tour based on its price.
     *
     * @param price the price which the tour is supposed to be priced at.
     * @return a specific tour with a certain price.
     */
    public Tour getTourByPrice(Float price) throws TourPlannerException {
        for (Tour currTour : tours) {
            String currTourId = currTour.getId();
            if (currTour.getPrice() == price && !iteratedTourIds.contains(currTourId)) {
                iteratedTourIds.add(currTourId);
                return currTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for a specific tour based on its name.
     *
     * @param name the name which the tour is supposed to be named.
     * @return a specific tour with a certain name.
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
     * Getter for a specific tour based on its ID.
     *
     * @param id the ID which the tour is supposed to have.
     * @return a specific tour with a certain ID.
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
     * Main method for deleting a tour.
     */
    public void cut(Tour tour) {
        tours.remove(tour);
        tourCount--;
    }
}
