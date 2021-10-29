package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;

public class TourList {
    private static final String TOUR_NOT_FOUND_MESSAGE = "Tour code cannot be found. Please try another Tour code.";

    private static ArrayList<Tour> tours;
    private static ArrayList<String> tourCodes;
    private static ArrayList<Float> tourPrices;
    private static ArrayList<String> iteratedTourCodes;
    private static int tourCount;

    public TourList() {
        tours = new ArrayList<>();
        tourCodes = new ArrayList<>();
        tourPrices = new ArrayList<>();
        tourCount = 0;
    }

    public void add(Tour tour) {
        tours.add(tour);
        tourCodes.add(tour.getCode());
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
            String currTourCode = currTour.getCode();
            if (currTour.getPrice() == price && !iteratedTourCodes.contains(currTourCode)) {
                iteratedTourCodes.add(currTourCode);
                return currTour;
            }
        }
        throw new TourPlannerException(TOUR_NOT_FOUND_MESSAGE);
    }

    public Tour getTourByCode(String code) throws TourPlannerException {
        for (int i = 0; i < tourCount; i++) {
            Tour currentTour = tours.get(i);
            if (currentTour.getCode().equals(code)) {
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
