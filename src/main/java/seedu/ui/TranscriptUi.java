package seedu.ui;

import seedu.duke.Duke;
import seedu.module.GradedModule;
import seedu.module.UngradedModule;
import seedu.user.Profile;

import java.time.LocalDateTime;

public class TranscriptUi {
    private static final String CENTER_ALIGN_SPACE = "\t\t\t\t\t\t\t\t";
    private static final String UniName = CENTER_ALIGN_SPACE + "--\tNational University of Singapore\t--";
    private static final String transcriptName = CENTER_ALIGN_SPACE + "\t--\tUnofficial Transcript\t--";
    private static final String notice = "This is not an official transcript issued by the Office of the Registrar.";
    private static LocalDateTime now;
    private static String divider = "\n" + CENTER_ALIGN_SPACE + "\t------------------------------";
    private static final int MODULE_CODE_LENGTH = 10;
    private static final int MODULE_TITLE_LENGTH = 70;
    private static final int CREDITS_LENGTH = 7;
    private static final int GRADE_LENGTH = 5;
    private static final String SEPARATION_SPACE = "  ";
    private static final String DELIMITER_SPACE = " ";
    private static final String HEADING_GRADE = "GRADE";
    private static final String HEADING_MODULE = "MODULE";
    private static final String HEADING_CREDITS = "CREDITS";
    public static double mcsCompleted = 0;


    public static void printIntroduction() {
        System.out.println(UniName);
        System.out.println(transcriptName + divider);
        now = LocalDateTime.now();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int year = now.getYear();
        String dateOfIssuance = day + "-" + month + "-" + year;
        System.out.println("\nDate Issued : " + dateOfIssuance + "\n");
    }

    public static void printHeadings() {
        System.out.print(HEADING_MODULE);
        for (int i = 0; i < MODULE_CODE_LENGTH - 6; i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.print(SEPARATION_SPACE);
        for (int i = 0; i < MODULE_TITLE_LENGTH; i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.print(SEPARATION_SPACE);
        System.out.print(HEADING_GRADE);
        System.out.print(SEPARATION_SPACE);
        System.out.println(HEADING_CREDITS + "\n");
    }

    public static void printGradedModules(GradedModule module) {
        String moduleCode = module.getModuleCode();
        String moduleTitle = module.getTitle();
        System.out.print(moduleCode);
        for (int i = 0; i < MODULE_CODE_LENGTH - moduleCode.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.print(SEPARATION_SPACE + moduleTitle);
        for (int i = 0; i < MODULE_TITLE_LENGTH - moduleTitle.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        String moduleGrade = SEPARATION_SPACE + module.getGrade();
        double moduleCredits = module.getModuleCredit();
        System.out.print(SEPARATION_SPACE + moduleGrade);
        for (int i = 0; i < GRADE_LENGTH - moduleGrade.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.println(SEPARATION_SPACE + DELIMITER_SPACE + moduleCredits);
        mcsCompleted = mcsCompleted + moduleCredits;
    }

    public static void printUngradedModules(UngradedModule module) {
        String moduleCode = module.getModuleCode();
        String moduleTitle = module.getTitle();
        System.out.print(moduleCode);
        for (int i = 0; i < MODULE_CODE_LENGTH - moduleCode.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.print(SEPARATION_SPACE + moduleTitle);
        for (int i = 0; i < MODULE_TITLE_LENGTH - moduleTitle.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        String moduleGrade = SEPARATION_SPACE + module.getGrade();
        double moduleCredits = module.getModuleCredit();
        System.out.print(SEPARATION_SPACE + moduleGrade);
        for (int i = 0; i < GRADE_LENGTH - moduleGrade.length(); i++) {
            System.out.print(DELIMITER_SPACE);
        }
        System.out.println(SEPARATION_SPACE + DELIMITER_SPACE + moduleCredits);
        mcsCompleted = mcsCompleted + moduleCredits;
    }


    public static void printConclusion() {
        System.out.println("\nTotal Credits Fulfilled : " + mcsCompleted);
        System.out.println();
        Profile currentProfile = Duke.getProfileInUse();
        TextUi.printCap(currentProfile.getCap());
        System.out.println("\n\t" + notice);
    }
}
