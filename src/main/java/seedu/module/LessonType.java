package seedu.module;

public enum LessonType {
    LECTURE("LEC"),
    TUTORIAL("TUT"),
    LABORATORY("LAB");

    private final String txt;

    LessonType(final String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return txt;
    }
}
