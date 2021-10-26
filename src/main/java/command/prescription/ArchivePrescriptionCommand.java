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
    private static Logger logger = Logger.getLogger("Delete Order");

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

        Date prescribeArchiveDate = null;
        String prescriptionArchiveDateStr = parameters.get(CommandParameters.DATE);
        try {
            prescribeArchiveDate = DateParser.stringToDate(prescriptionArchiveDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Medicine> filteredPrescription = new ArrayList<>();

        assert (filteredPrescription != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Prescription)) {
                continue;
            }
            Prescription prescription = (Prescription) medicine;
            Date prescriptionDate = DateParser.removeTime(prescription.getDate());
            if (prescriptionDate.before(prescribeArchiveDate)
                    || prescriptionDate.equals(prescribeArchiveDate)) {
                filteredPrescription.add(prescription);
            }
        }

        for (Medicine medicine : filteredPrescription) {
            medicines.remove(medicine);
        }

        Storage storage = Storage.getInstance();
        storage.archiveData(filteredPrescription);
        storage.saveData(medicines);
        ui.print("Archived prescriptions from " + DateParser.dateToString(prescribeArchiveDate));
        logger.log(Level.INFO, "Successful archive of prescriptions");
    }
}

