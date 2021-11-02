package medbot.list;


import static medbot.ui.Ui.END_LINE;

public class MedicalStaffList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int staffId) {
        return "No Staff with ID " + staffId + " found." + END_LINE;
    }

    @Override
    protected String getAlreadyHiddenErrorMessage(int staffId) {
        return "The staff with ID " + staffId + " is already hidden." + END_LINE;
    }

    @Override
    protected String getAlreadyShownErrorMessage(int staffId) {
        return "The staff with ID " + staffId + " is already not-hidden." + END_LINE;
    }

    @Override
    protected String getIcIsDuplicate(String icNumber) {
        return "The staff with IC " + icNumber + " is already in the record." + END_LINE;    }
}
