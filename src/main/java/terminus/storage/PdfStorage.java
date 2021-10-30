package terminus.storage;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import terminus.common.CommonFormat;
import terminus.common.Messages;
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
     * @param module The related module name for the pdf.
     * @param action The operation type to determine which operation to execute.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void execute(ModuleManager moduleManager, String module, StorageActionEnum action)
            throws InvalidFileException {
        switch (action) {
        case EXPORT:
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
     * @param module The folder name where all notes in it should be written into the pdf file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    private void exportModuleNotes(ModuleManager moduleManager, String module) throws InvalidFileException {
        Document tempDocument = new Document();
        Path pdfFile = getAppendPath(baseDirectory, appendFileExtension(module));
        ContentManager<Note> contentManager = moduleManager.getModule(module).getContentManager(Note.class);
        ArrayList<Note> noteArrayList = contentManager.getContents();
        writeToPdf(tempDocument, pdfFile, noteArrayList);
    }

    /**
     * Writes data into a newly created pdf file.
     *
     * @param tempDocument The document representing the pdf file.
     * @param pdfFile The filepath of the pdf file.
     * @param noteArrayList The listr of notes contents to be written into the pdf file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    private void writeToPdf(Document tempDocument, Path pdfFile, ArrayList<Note> noteArrayList)
            throws InvalidFileException {
        try {
            final PdfWriter writer = PdfWriter.getInstance(tempDocument, new FileOutputStream(pdfFile.toString()));
            Font header = FontFactory
                    .getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_HEADER_SIZE, Font.BOLD, BaseColor.BLACK);
            Font text = FontFactory.getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_SIZE, BaseColor.BLACK);
            tempDocument.open();
            for (Note note : noteArrayList) {
                Paragraph title = new Paragraph(note.getName(), header);
                Paragraph content = new Paragraph(note.getData(), text);
                tempDocument.add(title);
                tempDocument.add(content);
            }
            tempDocument.close();
            writer.close();
        } catch (DocumentException e) {
            throw new InvalidFileException(Messages.FAIL_TO_EXPORT);
        } catch (FileNotFoundException e) {
            throw new InvalidFileException(String.format(Messages.ERROR_MISSING_FILE, pdfFile));
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
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
