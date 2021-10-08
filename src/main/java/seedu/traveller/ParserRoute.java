package seedu.traveller;

public class ParserRoute {
    private static String line = null;
    private static String[] arr;
    private static String[] deadline;
    private static String[] event;

    public String input() {
        return line;
    }

    public String data() {
        return line.substring(1);
    }

    public String keyword() {
        arr = line.split(" ", 2);
        return arr[0];
    }

    public String item() {
        arr = line.split(" ", 2);
        return arr[1];
    }

    public void feed(String input) {
        line = input;
    }

    public String firstPhrase() {
        arr = line.split(" ", 10);
        return arr[1];
    }

    public String secondPhrase() {
        arr = line.split(" ", 10);
        return arr[2];
    }

    public String thirdPhrase() {
        arr = line.split(" ", 10);
        return arr[3];
    }

}




