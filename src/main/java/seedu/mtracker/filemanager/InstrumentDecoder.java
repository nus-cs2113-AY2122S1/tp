package seedu.mtracker.filemanager;

import seedu.mtracker.error.InvalidInstrumentInFileError;
import seedu.mtracker.model.InstrumentManager;
import seedu.mtracker.ui.TextUi;

import java.util.List;

public class InstrumentDecoder {

    public static final String FILE_SEPARATOR = ";";
    public static final int SPLIT_FUNCTION_LIMIT_VALUE = -1;

    public static final String TYPE_CRYPTO = "Crypto";
    public static final String TYPE_STOCK = "Stock";
    public static final String TYPE_ETF = "Etf";
    public static final String TYPE_FOREX = "Forex";

    public static final int TYPE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int CURR_PRICE_INDEX = 2;
    public static final int SENTIMENT_INDEX = 3;


    public static void readFile(InstrumentManager instrumentManager, List<String> fileData) {
        fileData.stream()
                .forEach((line) -> {
                    String[] textSegment = line.split(FILE_SEPARATOR, SPLIT_FUNCTION_LIMIT_VALUE);
                    try {
                        addSavedInstrumentToList(instrumentManager, textSegment);
                    } catch (InvalidInstrumentInFileError e) {
                        TextUi.showErrorMessage(e);
                    }
                });
    }

    private static void addSavedInstrumentToList(InstrumentManager instrumentManager, String[] textSegment)
            throws InvalidInstrumentInFileError {
        switch (textSegment[TYPE_INDEX]) {
        case TYPE_CRYPTO:
            CryptoDecoder.addCryptoToList(textSegment, instrumentManager);
            break;
        case TYPE_STOCK:
            StockDecoder.addStockToList(textSegment, instrumentManager);
            break;
        case TYPE_FOREX:
            ForexDecoder.addForexToList(textSegment, instrumentManager);
            break;
        case TYPE_ETF:
            EtfDecoder.addEtfToList(textSegment, instrumentManager);
            break;
        default:
            throw new InvalidInstrumentInFileError();
        }
    }
}
