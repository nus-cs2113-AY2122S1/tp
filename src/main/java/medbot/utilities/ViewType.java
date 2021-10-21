package medbot.utilities;

public enum ViewType {
    PATIENT_INFO, SCHEDULER, MEDICAL_STAFF_INFO;

    public static ViewType getNextView(ViewType viewType) {
        switch (viewType) {
        case PATIENT_INFO:
            return ViewType.MEDICAL_STAFF_INFO;
        case MEDICAL_STAFF_INFO:
            return ViewType.SCHEDULER;
        default:
            return ViewType.PATIENT_INFO;
        }
    }
}
