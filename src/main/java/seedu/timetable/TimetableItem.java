package seedu.timetable;

public class TimetableItem implements Comparable<TimetableItem> {
    private String classNo;
    private String module;
    private String day;
    private LessonType lessonType;
    private String start;
    private String end;
    private String venue;

    /**
     * Create Timetable item.
     * 
     * @param day day
     * @param lessonType type
     * @param start start in seconds from 00:00
     * @param end end in seconds 00:00
     * @param venue venue
     */
    public TimetableItem(String classNo, String module, String day, LessonType lessonType,
            String start, String end, String venue) {
        this.classNo = classNo;
        this.module = module;
        this.day = day;
        this.lessonType = lessonType;
        this.start = start;
        this.end = end;
        this.venue = venue;
    }

    /**
     * Get day string.
     * 
     * @return
     */
    public String getDay() {
        return this.day;
    }

    /**
     * Get start time as integer.
     * 
     * @return
     */
    public int getStart() {
        return Integer.parseInt(this.start);
    }

    @Override
    public int compareTo(TimetableItem item) {
        return this.getStart() - item.getStart();
    }
}
