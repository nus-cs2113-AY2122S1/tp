package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import inventory.Medicine;
import utilities.parser.DateParser;
import utilities.parser.OrderValidator;
import utilities.ui.Ui;
import utilities.storage.Storage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author RemusTeo

/**
 * Archive prescription based on user input given date.
 */
public class ArchivePrescriptionCommand extends Command {
    private static Logger logger = Logger.getLogger("ArchivePrescription");

    public ArchivePrescriptionCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start archiving of prescription");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {CommandParameters.DATE};
        String[] optionalParameters = {};

        OrderValidator orderValidator = new OrderValidator();
        boolean isInvalidParameter = orderValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.ARCHIVE_PRESCRIPTION_COMMAND, true);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful archive of prescription");
            return;
        }

        boolean isInvalidParameterValues = orderValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.ARCHIVE_PRESCRIPTION_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            logger.log(Level.INFO, "Unsuccessful archive of prescription");
            return;
        }

        Date prescriptionArchiveDate = null;
        String prescriptionArchiveDateStr = parameters.get(CommandParameters.DATE);
        try {
            prescriptionArchiveDate = DateParser.stringToDate(prescriptionArchiveDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Medicine> filteredPrescriptions = prescriptionsToArchive(medicines, prescriptionArchiveDate);
        removeFromPrescriptions(medicines, filteredPrescriptions);

        Storage storage = Storage.getInstance();
        storage.archiveData(filteredPrescriptions);
        storage.saveData(medicines);
        ui.print("Archived prescriptions from " + DateParser.dateToString(prescriptionArchiveDate));
        logger.log(Level.INFO, "Successful archive of prescriptions");
    }

    /**
     * Checks through all prescriptions and look for records that have prescription date <= specified date.
     *
     * @param medicines               Arraylist of all medicines.
     * @param prescriptionArchiveDate Date that user specified to archive.
     * @return Arraylist of prescriptions that meet the archive requirements.
     */
    private ArrayList<Medicine> prescriptionsToArchive(ArrayList<Medicine> medicines, Date prescriptionArchiveDate) {
        ArrayList<Medicine> filteredPrescriptions = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Prescription)) {
                continue;
            }
            Prescription prescription = (Prescription) medicine;
            Date prescriptionDate = DateParser.removeTime(prescription.getDate());
            if (prescriptionDate.before(prescriptionArchiveDate)
                    || prescriptionDate.equals(prescriptionArchiveDate)) {
                filteredPrescriptions.add(prescription);
            }
        }
        return filteredPrescriptions;
    }

    /**
     * Removal of prescriptions from prescription list after archive.
     *
     * @param medicines             Arraylist of all medicines.
     * @param filteredPrescriptions Arraylist of prescriptions that meet the archive requirements.
     */
    private void removeFromPrescriptions(ArrayList<Medicine> medicines, ArrayList<Medicine> filteredPrescriptions) {
        for (Medicine medicine : filteredPrescriptions) {
            medicines.remove(medicine);
        }
    }
}

