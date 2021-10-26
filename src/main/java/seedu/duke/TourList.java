//package seedu.duke;
//
//import java.util.ArrayList;
//
//public class TourList {
//    private static final String TOUR_NOT_FOUND_MESSAGE = "Tour code cannot be found. Please try another Tour code.";
//
//    private static ArrayList<Tour> tours;
//    private static int tourCount;
//
//    public TourList() {
//        tours = new ArrayList<>();
//        tourCount = 0;
//    }
//
//    public void add(Tour tour) {
//        tours.add(tour);
//        tourCount++;
//    }
//
//    public ArrayList<Tour> getTours() {
//        return tours;
//    }
//
//    public int getTourCount() {
//        return tourCount;
//    }
//
//    public Tour getTourByIndex(int index) {
//        return tours.get(index);
//    }
//
//    public Tour getTourByCode(String code) throws TourPlannerException {
//        for (int i = 0; i < tourCount; i++) {
//            Tour currentTour = tours.get(i);
//            if (currentTour.getCode().equals(code)) {
//                return currentTour;
//            }
//        }
//    }
//
//    public void cut(Tour tour) {
//        tours.remove(tour);
//        tourCount--;
//    }
//}
