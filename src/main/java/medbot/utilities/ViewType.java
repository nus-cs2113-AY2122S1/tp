package medbot.utilities;

public enum ViewType {
    PATIENT_INFO, SCHEDULER, MEDICAL_STAFF_INFO;

    /**
     * Returns the next ViewType based on the given viewType.
     *
     * <p>Order is fixed at PATIENT_INFO, SCHEDULER, MEDICAL_STAFF_INFO
     *
     * @param viewType a certain ViewType value
     * @return the next viewType
     */
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
