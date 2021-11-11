package seedu.module;

import java.util.ArrayList;
import java.util.Date;

public class Weeks {

    private Date start;
    private Date end;
    private ArrayList<Integer> weeks;

    public Weeks(ArrayList<Integer> weeks) {
        this.weeks = weeks;
    }

    @Override
    public String toString() {
        if (weeks != null) {
            return weeks.toString();
        }
        return start.toString() + end.toString();
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public ArrayList<Integer> getWeeks() {
        return weeks;
    }

    public void setWeeks(ArrayList<Integer> weeks) {
        this.weeks = weeks;
    }
}
