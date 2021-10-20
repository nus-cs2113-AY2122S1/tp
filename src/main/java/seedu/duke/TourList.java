package seedu.duke;

import java.util.ArrayList;

public class TourList {
    private static ArrayList<Tour> tours;
    private static int tourCount = 0;

    public TourList() {
        tours = new ArrayList<>();
        tourCount = 0;
    }

    public void add(Tour tour) {
        tours.add(tour);
        tourCount++;
    }

    public int getTourCount() {
        return tourCount;
    }

    public Tour getTour(String code) {
        for (int i = 0; i < tourCount; i++){
           if (tours.get(i).getCode().equals(code)){
               return tours.get(i);
           }
        }
        return null;
    }
}
