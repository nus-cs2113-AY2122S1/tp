import gordon.util.Parser;
import gordon.kitchen.Cookbook;

public class Gordon {
    Parser parser;
    Cookbook mainCookbook;

    public Gordon() {
        parser = new Parser();
        mainCookbook = new Cookbook();
    }

    public void run() {
        parser.parseMaster(mainCookbook);
    }

    public static void main(String[] args) {
        new Gordon().run();
    }
}
