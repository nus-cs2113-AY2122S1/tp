package seedu.comparator;

import seedu.module.Lesson;
import java.util.Comparator;

public class ClassNumComparator implements Comparator<Lesson> {
    public int compare(Lesson l1, Lesson l2) {
        String l1ClassNo = l1.getClassNo();
        String l2ClassNo = l2.getClassNo();

        return l1ClassNo.compareTo(l2ClassNo);
    }
}
