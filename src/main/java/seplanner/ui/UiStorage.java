package seplanner.ui;

import seplanner.constants.Constants;

public class UiStorage extends Ui {
    public static void printInvalidModuleMessage() {
        UiGeneral.printLineSeparator();
        System.out.println(Constants.STORAGE_INVALID_MODULE);
        printWarningMessage();
    }

    public static void printInvalidMappingMessage() {
        UiGeneral.printLineSeparator();
        System.out.println(Constants.STORAGE_INVALID_MAPPING);
        printWarningMessage();
    }

    public static void printInvalidUniversityMessage() {
        UiGeneral.printLineSeparator();
        System.out.println(Constants.STORAGE_INVALID_UNIVERSITY);
        printWarningMessage();
    }

        public static void printWarningMessage() {
        System.out.println(Constants.STORAGE_WARNING_MESSAGE);
    }
}