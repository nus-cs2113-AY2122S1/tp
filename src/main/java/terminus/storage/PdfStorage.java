package terminus.storage;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import terminus.common.CommonFormat;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

/**
 * PdfStorage to handles any pdf related operations.
 */
public class PdfStorage extends Storage {

    private Path baseDirectory;
    private static final String FILE_EXTENSION = ".pdf";

    public PdfStorage(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    /**
     * Executes the specified operation with the given arguments.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module        The related module name for the pdf.
     * @param action        The operation type to determine which operation to execute.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void execute(ModuleManager moduleManager, String module, StorageActionEnum action)
        throws InvalidFileException {
        switch (action) {
        case EXPORT:
            TerminusLogger.info(String.format("Exporting notes into a pdf file from module folder : %s", module));
            exportModuleNotes(moduleManager, module);
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    /**
     * Exports the notes in the module folder into a pdf file.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param module        The folder name where all notes in it should be written into the pdf file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void exportModuleNotes(ModuleManager moduleManager, String module) throws InvalidFileException {
        assert moduleManager.getModule(module) != null;
        Path pdfFile = getAppendPath(baseDirectory, appendFileExtension(module));
        ContentManager<Note> contentManager = moduleManager.getModule(module).getContentManager(Note.class);
        ArrayList<Note> noteArrayList = contentManager.getContents();
        writeToPdf(pdfFile, noteArrayList);
    }

    /**
     * Writes data into a newly created pdf file.
     *
     * @param pdfFile       The filepath of the pdf file.
     * @param noteArrayList The listr of notes contents to be written into the pdf file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    private void writeToPdf(Path pdfFile, ArrayList<Note> noteArrayList)
        throws InvalidFileException {
        try {
            if (noteArrayList.isEmpty()) {
                throw new Exception();
            }
            Document tempDocument = new Document();
            final PdfWriter writer = PdfWriter.getInstance(tempDocument, new FileOutputStream(pdfFile.toString()));
            Font header = FontFactory
                .getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_HEADER_SIZE, Font.BOLD, Color.BLACK);
            Font text = FontFactory.getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_SIZE, Color.BLACK);
            tempDocument.open();
            for (Note note : noteArrayList) {
                Paragraph title = new Paragraph(note.getName(), header);
                Paragraph content = new Paragraph(note.getData(), text);
                tempDocument.add(title);
                tempDocument.add(content);
            }
            tempDocument.close();
            writer.close();
        } catch (Exception e) {
            throw new InvalidFileException(Messages.FAIL_TO_EXPORT);
        }
    }

    /**
     * Appends the pdf file extension to the given string.
     *
     * @param name The filename without the extension.
     * @return The complete filename with the extension of a pdf file.
     */
    private String appendFileExtension(String name) {
        return name + FILE_EXTENSION;
    }
}
