package seedu.duke.commands.tours;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.Tour;

import java.util.ArrayList;

public class CutTourCommand extends Command {
    private final String tourId;
    private Tour tour;

    /**
     * Class constructor for CutTourCommand.
     *
     * @param tourId ID of to-be-deleted tour in the tour list
     */
    public CutTourCommand(String tourId) {
        this.tourId = tourId;
    }

    /**
     * Executes the deletion of a specific client from client list, corresponding to his/her index in the list.
     */
    @Override
    public void execute() {
        try {
            cutTour();
            cutTourPackage();
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("INVALID: Index out of bounds");
        } catch (TourPlannerException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void cutTour() throws TourPlannerException {
        this.tour = tours.getTourById(tourId);
        int newTourCount = tours.getTourCount() - 1;
        ui.showCut(tour);
        tours.cut(tour);
        assert newTourCount == tours.getTourCount();
        assert newTourCount >= 0;
    }

    private void cutTourPackage() {
        ArrayList<ClientPackage> clientPackagesWithTour = clientPackages.getClientPackageByTour(tour);
        for (ClientPackage clientPackage: clientPackagesWithTour) {
            System.out.println();
            ui.showCut(clientPackage);
            clientPackages.cut(clientPackage);
        }
    }

}
