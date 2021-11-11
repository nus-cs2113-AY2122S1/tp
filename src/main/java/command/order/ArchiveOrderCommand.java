package command.order;

import command.Command;
import command.CommandParameters;
import command.CommandSyntax;
import inventory.Medicine;
import inventory.Order;
import utilities.parser.DateParser;
import utilities.parser.MedicineValidator;
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
 * Archive orders based on user input given date.
 */
public class ArchiveOrderCommand extends Command {
    private static Logger logger = Logger.getLogger("ArchiveOrder");

    public ArchiveOrderCommand(LinkedHashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void execute() {
        logger.log(Level.INFO, "Start archiving of order");

        Ui ui = Ui.getInstance();
        ArrayList<Medicine> medicines = Medicine.getInstance();

        String[] requiredParameters = {CommandParameters.DATE};
        String[] optionalParameters = {};

        MedicineValidator validator = new OrderValidator();
        boolean isInvalidInput = validator.containsInvalidParametersAndValues(ui, medicines, parameters,
                requiredParameters, optionalParameters, CommandSyntax.ARCHIVE_ORDER_COMMAND, true, validator);
        if (isInvalidInput) {
            logger.log(Level.INFO, "Unsuccessful archive of order");
            return;
        }

        Date orderArchiveDate = null;
        String orderArchiveDateStr = parameters.get(CommandParameters.DATE);
        try {
            orderArchiveDate = DateParser.stringToDate(orderArchiveDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Medicine> filteredOrders = ordersToArchive(medicines, orderArchiveDate);
        removeFromOrders(medicines, filteredOrders);

        Storage storage = Storage.getInstance();
        storage.archiveData(filteredOrders);
        storage.saveData(medicines);
        ui.print("Archived " + filteredOrders.size() + " delivered orders from "
                + DateParser.dateToString(orderArchiveDate));
        logger.log(Level.INFO, "Successful archive of order");
    }

    /**
     * Checks through all orders and look for records that are DELIVERED and have order date <= specified date.
     *
     * @param medicines        Arraylist of all medicines.
     * @param orderArchiveDate Date that user specified to archive.
     * @return Arraylist of orders that meet the archive requirements.
     */
    private ArrayList<Medicine> ordersToArchive(ArrayList<Medicine> medicines, Date orderArchiveDate) {
        ArrayList<Medicine> filteredOrders = new ArrayList<>();
        for (Medicine medicine : medicines) {
            if (!(medicine instanceof Order)) {
                continue;
            }
            Order order = (Order) medicine;
            Date orderDate = DateParser.removeTime(order.getDate());
            if (order.getStatus().equalsIgnoreCase("DELIVERED")) {
                if (orderDate.before(orderArchiveDate) || orderDate.equals(orderArchiveDate)) {
                    filteredOrders.add(order);
                }
            }
        }
        return filteredOrders;
    }

    /**
     * Removal of orders from order list after archive.
     *
     * @param medicines      Arraylist of all medicines.
     * @param filteredOrders Arraylist of orders that meet the archive requirements.
     */
    private void removeFromOrders(ArrayList<Medicine> medicines, ArrayList<Medicine> filteredOrders) {
        for (Medicine medicine : filteredOrders) {
            medicines.remove(medicine);
        }
    }
}

