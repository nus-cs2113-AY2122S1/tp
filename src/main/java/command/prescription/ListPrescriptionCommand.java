package command.prescription;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Prescription;
import utilities.comparators.PrescriptionComparator;
import inventory.Medicine;
import utilities.parser.DateParser;
import utilities.parser.PrescriptionValidator;
import utilities.ui.Ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//@@author alvintan01
/**
 * Helps to process the listprescription command together with filters and sort.
 */

public class ListPrescriptionCommand extends Command {
    private static Logger logger = Logger.getLogger("ListPrescription");

    public ListPrescriptionCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start listing of prescription records");
        Ui ui = Ui.getInstance();

        String[] requiredParameter = {};
        String[] optionalParameters = {CommandParameters.ID, CommandParameters.NAME, CommandParameters.QUANTITY,
                CommandParameters.CUSTOMER_ID, CommandParameters.DATE, CommandParameters.STAFF,
                CommandParameters.STOCK_ID,
                CommandParameters.SORT, CommandParameters.REVERSED_SORT};

        PrescriptionValidator prescriptionValidator = new PrescriptionValidator();
        boolean isInvalidParameter = prescriptionValidator.containsInvalidParameters(ui, parameters, requiredParameter,
                optionalParameters, CommandSyntax.LIST_PRESCRIPTION_COMMAND, false);

        if (isInvalidParameter) {
            logger.log(Level.WARNING, "Invalid parameters given by user");
            return;
        }

        ArrayList<Medicine> medicines = Medicine.getInstance();

        boolean isInvalidParameterValues = prescriptionValidator.containsInvalidParameterValues(ui, parameters,
                medicines,
                CommandSyntax.LIST_PRESCRIPTION_COMMAND);

        if (isInvalidParameterValues) {
            logger.log(Level.WARNING, "Invalid parameters values given by user");
            return;
        }

        ArrayList<Prescription> filteredPrescriptions = new ArrayList<>();

        assert (filteredPrescriptions != null) : "Array is not initialised";

        for (Medicine medicine : medicines) {
            if (medicine instanceof Prescription) {
                filteredPrescriptions.add((Prescription) medicine);
            }
        }
        filteredPrescriptions = filterPrescription(parameters, filteredPrescriptions);

        ui.printPrescriptions(filteredPrescriptions);
        logger.log(Level.INFO, "Successful listing of prescription");
    }


    /**
     * Helps to filter prescription records based on the user's input.
     *
     * @param parameters            HashMap Key-Value set for parameter and user specified parameter value.
     * @param filteredPrescriptions Arraylist of Prescription objects.
     * @return Arraylist of filtered Prescription objects based on the user's parameters values.
     */
    private ArrayList<Prescription> filterPrescription(LinkedHashMap<String, String> parameters,
                                                       ArrayList<Prescription> filteredPrescriptions) {
        for (String parameter : parameters.keySet()) {
            String parameterValue = parameters.get(parameter);
            switch (parameter) {
            case CommandParameters.ID:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getPrescriptionId() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.NAME:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getMedicineName().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.QUANTITY:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getQuantity() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.CUSTOMER_ID:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getCustomerId().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.DATE:
                try {
                    Date date = DateParser.stringToDate(parameterValue);
                    filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                            .filter((m) -> m.getDate().equals(date))
                            .collect(Collectors.toList());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case CommandParameters.STAFF:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getStaff().toUpperCase().contains(parameterValue.toUpperCase()))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.STOCK_ID:
                filteredPrescriptions = (ArrayList<Prescription>) filteredPrescriptions.stream()
                        .filter((d) -> d.getStockId() == Integer.parseInt(parameterValue))
                        .collect(Collectors.toList());
                break;
            case CommandParameters.SORT:
                filteredPrescriptions.sort(new PrescriptionComparator(parameterValue.toLowerCase(), false));
                break;
            case CommandParameters.REVERSED_SORT:
                filteredPrescriptions.sort(new PrescriptionComparator(parameterValue.toLowerCase(), true));
                break;
            default:
                return filteredPrescriptions;
            }
        }
        return filteredPrescriptions;
    }
}
