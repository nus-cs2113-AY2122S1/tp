package seedu.duke.commands.clientpackages;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.ClientPackage;

public class CutClientPackageCommand extends Command {
    private final String clientPackageId;
    private ClientPackage clientPackage;

    /**
     * Class constructor for CutClientPackageCommand.
     *
     * @param clientPackageId ID of to-be-deleted clientPackage in the clientPackage list
     */
    public CutClientPackageCommand(String clientPackageId) {
        this.clientPackageId = clientPackageId;
    }

    /**
     * Executes the deletion of a specific clientPackage from clientPackage list, according to the clientPackageId.
     * If client package id cannot be found, nothing will be deleted from the list.
     */
    @Override
    public void execute() {
        try {
            this.clientPackage = clientPackages.getClientPackageById(clientPackageId);
            int newClientPackageCount = clientPackages.getClientPackageCount() - 1;
            ui.showCut(clientPackage);
            clientPackages.cut(clientPackage);
            assert newClientPackageCount == clientPackages.getClientPackageCount();
            assert newClientPackageCount >= 0;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("INVALID: Index out of bounds");
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}
