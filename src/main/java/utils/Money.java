package utils;

public class Money {

    public static Double truncate(Double val) {
        return Math.round(val * 100) / 100.0;
    }
}
