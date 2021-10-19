package expiryeliminator.parser.argparser;

/**
 * Parses unit.
 */
public class UnitParser extends SingleArgParser<String> {
    /**
     * Parses unit.
     *
     * @param unitString String to be parsed as a unit.
     * @return Parsed unit.
     */
    @Override
    public String parse(String unitString) {
        assert unitString != null;
        return unitString;
    }
}
