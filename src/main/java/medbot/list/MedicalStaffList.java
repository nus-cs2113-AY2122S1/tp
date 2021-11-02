package medbot.list;


import static medbot.ui.Ui.END_LINE;

public class MedicalStaffList extends PersonList {

    @Override
    protected String getPersonNotFoundErrorMessage(int staffId) {
        return "No Staff with ID " + staffId + " found." + END_LINE;
    }

    @Override
    protected String getAlreadyArchivedErrorMessage(int staffId) {
        return "The staff with ID " + staffId + " is already archived." + END_LINE;
    }

    @Override
    protected String getAlreadyUnarchivedErrorMessage(int staffId) {
        return "The staff with ID " + staffId + " is already unarchived." + END_LINE;
    }

    @Override
    protected String getIcIsDuplicate(String icNumber) {
        return "The staff with IC " + icNumber + " is already in the record." + END_LINE;    }
}
