package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;

public class TourList {
    private static final String TOUR_NOT_FOUND_MESSAGE = "Tour cannot be found. Please try another tour ID";

    private final ArrayList<Tour> tours;
    private final ArrayList<String> tourIds;
    private final ArrayList<Float> tourPrices;
    private ArrayList<String> iteratedTourIds;
    private int tourCount;

    public TourList() {
        tours = new ArrayList<>();
        tourIds = new ArrayList<>();
        tourPrices = new ArrayList<>();
        tourCount = 0;
    }

    public void add(Tour tour) {
        tours.add(tour);
        tourIds.add(tour.getId());
        tourPrices.add(tour.getPrice());
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
        Collections.sort(tourIds);
        return tourIds;
    }

    public int getTourCount() {
        return tourCount;
    }

    public Tour getTourByIndex(int index) {
        return tours.get(index);
    }

    public void initTempArray() {
        iteratedTourIds = new ArrayList<String>();
    }

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

    public Tour getTourById(String id) throws TourPlannerException {
        for (int i = 0; i < tourCount; i++) {
            Tour currentTour = tours.get(i);
            if (currentTour.getId().equals(id)) {
                return currentTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    public void cut(Tour tour) {
        tours.remove(tour);
        tourCount--;
    }
}
