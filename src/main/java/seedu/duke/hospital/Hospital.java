package seedu.duke.hospital;

import java.util.ArrayList;

public class Hospital {
    private final String name;
    private final Integer number;
    private final ArrayList<Doctor> doctorArrayList = new ArrayList<Doctor>();

    public Hospital(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    /**
     * Returns the hospital's name.
     *
     * @return Name of the hospital.
     */
    public String getHospitalName() {
        return name;
    }

    /**
     * Returns the hospital's number.
     *
     * @return Number of the hospital.
     */
    public Integer getHospitalNumber() {
        return number;
    }

    /**
     * Prints all the doctors' name in this hospital.
     */
    public void printDoctorNames() {
        int counter = 1;
        System.out.println("List of " + getHospitalName() + " doctors are shown below:");
        for (Doctor doctor : doctorArrayList) {
            System.out.format("% 3d.", counter);
            System.out.println(doctor.getDoctorName());
            counter++;
        }
    }

    /**
     * Gets all doctors associated to a hospital.
     * @return Array List of Doctors
     */
    public ArrayList<Doctor> getAllDoctors() {
        return doctorArrayList;
    }

    /**
     * Returns the doctor in doctorArrayList given the index.
     *
     * @param doctorIndex Index of doctor.
     * @return Doctor instance of given index is returned.
     */
    public Doctor getDoctor(int doctorIndex) {
        return doctorArrayList.get(doctorIndex);
    }

    /**
     * Creates new instance of Doctor and adds him into doctorArrayList.
     *
     * @param name Name of doctor.
     * @param number Number of doctor.
     */
    public void addDoctor(String name, Integer number) {
        doctorArrayList.add(new Doctor(name, number));
    }

    public String toString() {
        return String.format("%s (Hospital Name)\n%s (Hospital Number)", getHospitalName(), getHospitalNumber());
    }

}
