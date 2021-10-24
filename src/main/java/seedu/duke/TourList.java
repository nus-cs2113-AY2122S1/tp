package seedu.duke;

import java.util.ArrayList;
import java.util.Collections;

public class TourList {
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

    public Tour getTourByPrice(Float price) {
        for (Tour currTour : tours) {
            String currTourCode = currTour.getCode();
            if (currTour.getPrice() == price && !iteratedTourCodes.contains(currTourCode)) {
                iteratedTourCodes.add(currTourCode);
                return currTour;
            }
        }
        return null;
    }

    public Tour getTourByCode(String code) {
        for (int i = 0; i < tourCount; i++) {
            Tour currentTour = tours.get(i);
            if (currentTour.getCode().equals(code)) {
                return currentTour;
            }
        }
        return null;
    }
}
