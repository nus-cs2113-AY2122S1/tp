package command.dispense;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Dispense;
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

/**
 * Delete order based on user input given order id.
 */
public class ArchiveDispenseCommand extends Command {
    private static Logger logger = Logger.getLogger("Delete Order");

    public ArchiveDispenseCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start archiving of dispense");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {CommandParameters.DATE};
        String[] optionalParameters = {};

        OrderValidator dispenseValidator = new OrderValidator();
        boolean isInvalidParameter = dispenseValidator.containsInvalidParameters(ui, parameters, requiredParameters,
                optionalParameters, CommandSyntax.ARCHIVE_DISPENSE_COMMAND, true);
        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameter is specified by user");
            logger.log(Level.INFO, "Unsuccessful archive of order");
            return;
        }

        boolean isInvalidParameterValues = dispenseValidator.containsInvalidParameterValues(ui, parameters,
                medicines, CommandSyntax.ARCHIVE_DISPENSE_COMMAND);
        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            logger.log(Level.INFO, "Unsuccessful archive of order");
            return;
        }

        Date dispenseArchiveDate = null;
        String dispenseArchiveDateStr = parameters.get(CommandParameters.DATE);
        try {
            dispenseArchiveDate = DateParser.stringToDate(dispenseArchiveDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Medicine> filteredDispenses = new ArrayList<>();

        assert (filteredDispenses != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Dispense)) {
                continue;
            }
            Dispense dispense = (Dispense) medicine;
            if (dispense.getDate().before(dispenseArchiveDate) || dispense.getDate().equals(dispenseArchiveDate)) {
                filteredDispenses.add(dispense);
            }
        }

        for (Medicine medicine : filteredDispenses) {
            medicines.remove(medicine);
        }

        Storage storage = Storage.getInstance();
        storage.archiveData(filteredDispenses);
        storage.saveData(medicines);
        ui.print("Archived dispenses from " + DateParser.dateToString(dispenseArchiveDate));
        logger.log(Level.INFO, "Successful archive of dispense");
    }
}

