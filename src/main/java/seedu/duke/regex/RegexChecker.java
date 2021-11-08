package seedu.duke.regex;

import static java.lang.Math.min;

import java.util.regex.Pattern;

public class RegexChecker {

    public RegexChecker() {
    }

    public boolean isValidRiskLevel(String userLine) {
        return Pattern.matches("^[LMH]$", userLine);
    }

    public boolean isNotValidHospitalIndex(String userLine) {
        return !Pattern.matches("^[1-6]$", userLine);
    }

    public boolean isValidDoctorIndex(String userLine) {
        return Pattern.matches("^[1-5]$", userLine);
    }

    public boolean isValidAddElderly(String userLine) {
        return Pattern.matches("^addelderly u/[a-z0-9]+ n/[a-z0-9\\s]+ r/[a-z]{1}$", userLine.toLowerCase());
    }

    public boolean isValidAddMedicine(String userLine) {
        return Pattern.matches("^addmed u/[a-z0-9]+ m/[a-z0-9\\s]+ f/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidAddAppointment(String userLine) {
        boolean isValidWithoutPurpose = Pattern.matches("^addappt u/[a-z0-9]+ l/[a-z0-9\\s]+ d/[0-9]{8} t/[0-9]{4}$",
                userLine.toLowerCase());
        boolean isValidWithPurpose = Pattern.matches("^addappt u/[a-z0-9]+ l/[a-z0-9\\s]+ d/[0-9]{8} t/[0-9]{4} "
                + "p/[a-z0-9\\s]+$", userLine.toLowerCase());
        return isValidWithoutPurpose || isValidWithPurpose;
    }

    public boolean isValidViewMedicine(String userLine) {
        return Pattern.matches("^viewmed u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidViewAppointment(String userLine) {
        return Pattern.matches("^viewappt u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidAddNok(String userLine) {
        return Pattern.matches("^addnok u/[a-z0-9]+ k/[a-z0-9\\s]+ p/[0-9]{8}+ e/[A-Za-z0-9+_.-]+@(.+)+ "
                + "a/[a-z0-9\\s]+ r/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidEmail(String userEmail) {
        return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)+$", userEmail);
    }

    public boolean isValidHpNumber(String number) {
        return Pattern.matches("^[0-9]{8}$", number);
    }

    public boolean isValidViewNok(String userLine) {
        return Pattern.matches("^viewnok u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidAddRecord(String userLine) {
        return Pattern.matches("^addrec u/[a-z0-9]+ p/[0-9]{8}+ a/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidViewRec(String userLine) {
        return Pattern.matches("^viewrec u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidDeleteNok(String userLine) {
        return Pattern.matches("^deletenok u/[a-z0-9]+ n/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidDeleteMedicine(String userLine) {
        return Pattern.matches("^deletemed u/[a-z0-9]+ m/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidDeleteAppointment(String userLine) {
        return Pattern.matches("^deleteappt u/[a-z0-9]+ d/[0-9]{8} t/[0-9]{4}$", userLine.toLowerCase());
    }

    public boolean isValidFindMed(String userLine) {
        return Pattern.matches("^findbymed m/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidFindDiet(String userLine) {
        return Pattern.matches("^findbydiet d/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidFindByName(String userLine) {
        return Pattern.matches("^findbyname n/[a-z0-9\\s]+$", userLine.toLowerCase());
    }

    public boolean isValidDeleteElderlyByUsername(String userLine) {
        return Pattern.matches("^deleteelderly u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidStoreFileFromFilePath(String userLine) {
        return Pattern.matches("^store fp/[/a-z0-9.\\\\]+.json$", userLine.toLowerCase());
    }

    public boolean isValidLoadFileFromFilePath(String userLine) {
        return Pattern.matches("^load fp/[/a-z0-9.\\\\]+.json$", userLine.toLowerCase());
    }

    public boolean isValidSetBloodPressure(String userLine) {
        return Pattern.matches("^setbloodpressure u/[a-z0-9]+ s/[0-9]{2,3} d/[0-9]{2,3}$", userLine.toLowerCase());
    }

    public boolean isValidViewDietCommand(String userLine) {
        return Pattern.matches("^viewdiet u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidSetDietCommand(String userLine) {
        return Pattern.matches("^setdiet u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidSetVaccinationCommand(String userLine) {
        return Pattern.matches("^setvaccination u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidViewVaccinationCommand(String userLine) {
        return Pattern.matches("^viewvaccination u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidSetBirthdayCommand(String userLine) {
        return Pattern.matches("^setbirthday u/[a-z0-9]+ b/[0-9-]+$", userLine.toLowerCase());
    }

    public boolean isValidViewBirthdayCommand(String userLine) {
        return Pattern.matches("^viewbirthday u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidDeleteMedicalHistoryCommand(String userLine) {
        return Pattern.matches("^deletemedicalhistory u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidAddMedicalHistoryCommand(String userLine) {
        return Pattern.matches("^addmedicalhistory u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidViewMedicalHistoryCommand(String userLine) {
        return Pattern.matches("^addmedicalhistory u/[a-z0-9]+$", userLine.toLowerCase());
    }

    public boolean isValidViewBloodPressureCommand(String userLine) {
        return Pattern.matches("^viewbloodpressure u/[a-z0-9]+$", userLine.toLowerCase());
    }

    /**
     * Takes 2 strings and compare the distance(similarity) between them.
     *
     * @param firstString  First String to be compared.
     * @param secondString Second String to be compared.
     * @return Float containing the ratio of similarity between the two strings.
     */
    public float levenshteinDistance(String firstString, String secondString) {
        float result;
        int numberRows = firstString.length() + 1;
        int numberCols = secondString.length() + 1;
        int[][] levDistanceArray = new int[numberRows][numberCols];

        // Distance between row/column 0 and another with value i is i
        for (int i = 1; i < numberRows; ++i) {
            for (int j = 1; j < numberCols; ++j) {
                levDistanceArray[i][0] = i;
                levDistanceArray[0][j] = j;
            }
        }

        // Iterate through the rest to see the cost
        int cost;
        int columnIndex;
        int rowIndex = 1;
        for (columnIndex = 1; columnIndex < numberCols; ++columnIndex) {
            for (rowIndex = 1; rowIndex < numberRows; ++rowIndex) {
                if (firstString.charAt(rowIndex - 1) == secondString.charAt(columnIndex - 1)) {
                    cost = 0;
                } else {
                    cost = 2;
                }
                int deletionCost = levDistanceArray[rowIndex - 1][columnIndex] + 1;
                int insertionCost = levDistanceArray[rowIndex][columnIndex - 1] + 1;
                int subCost = levDistanceArray[rowIndex - 1][columnIndex - 1] + cost;
                levDistanceArray[rowIndex][columnIndex] = min(min(deletionCost, insertionCost), subCost);
            }
        }

        float totalLengthOfString = firstString.length() + secondString.length();
        result = (totalLengthOfString - levDistanceArray[rowIndex - 1][columnIndex - 1]) / totalLengthOfString;

        return result;
    }
}
