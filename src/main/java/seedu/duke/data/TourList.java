package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;

public class TourList {
    private final String TOUR_NOT_FOUND_MESSAGE = "Tour code cannot be found. Please try another Tour code.";

    private ArrayList<Tour> tours;
    private ArrayList<String> tourCodes;
    private ArrayList<Float> tourPrices;
    private ArrayList<String> iteratedTourCodes;
    private int tourCount;

    public TourList() {
        tours = new ArrayList<>();
        tourCodes = new ArrayList<>();
        tourPrices = new ArrayList<>();
        tourCount = 0;
    }

    public void add(Tour tour) {
        tours.add(tour);
        tourCodes.add(tour.getId());
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

    public ArrayList<String> getSortedTourCodes() {
        Collections.sort(tourCodes);
        return tourCodes;
    }

    public int getTourCount() {
        return tourCount;
    }

    public Tour getTourByIndex(int index) {
        return tours.get(index);
    }

    public void initTempArray() {
        iteratedTourCodes = new ArrayList<String>();
    }

    public Tour getTourByPrice(Float price) throws TourPlannerException {
        for (Tour currTour : tours) {
            String currTourCode = currTour.getId();
            if (currTour.getPrice() == price && !iteratedTourCodes.contains(currTourCode)) {
                iteratedTourCodes.add(currTourCode);
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
