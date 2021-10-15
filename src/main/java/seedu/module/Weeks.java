package seedu.module;

import com.google.gson.annotations.JsonAdapter;

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
}
