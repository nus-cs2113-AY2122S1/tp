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
     * Executes deletion of the specific tour and related client packages, according to the tourId.
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

    /**
     * Executes deletion of the specific tour from the tour list.
     *
     * @throws TourPlannerException if tour cannot be found based on the tour id
     */
    private void cutTour() throws TourPlannerException {
        this.tour = tours.getTourById(tourId);
        int newTourCount = tours.getTourCount() - 1;
        ui.showCut(tour);
        tours.cut(tour);
        assert newTourCount == tours.getTourCount();
        assert newTourCount >= 0;
    }

    /**
     * Executes deletion of the client packages containing the specific tour from the client package list.
     */
    private void cutTourPackage() {
        ArrayList<ClientPackage> clientPackagesWithTour = clientPackages.getClientPackageByTour(tour);
        for (ClientPackage clientPackage: clientPackagesWithTour) {
            System.out.println();
            ui.showCut(clientPackage);
            clientPackages.cut(clientPackage);
        }
    }

}
