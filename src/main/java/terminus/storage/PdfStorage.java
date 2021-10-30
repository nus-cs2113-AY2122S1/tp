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

public class PdfStorage extends Storage {

    private Path baseDirectory;
    private final String FILE_EXTENSION = ".pdf";

    private final String NO_DATA = "No note exists.";

    public PdfStorage(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

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

    private void exportModuleNotes(ModuleManager moduleManager, String module) throws InvalidFileException {
        Document tempDocument = new Document();
        Path pdfFile = getAppendPath(baseDirectory, appendFileExtension(module));
        ContentManager<Note> contentManager = moduleManager.getModule(module).getContentManager(Note.class);
        ArrayList<Note> noteArrayList = contentManager.getContents();
        writeToPdf(tempDocument, pdfFile, noteArrayList);
    }

    private void writeToPdf(Document tempDocument, Path pdfFile, ArrayList<Note> noteArrayList)
            throws InvalidFileException {
        try {
            PdfWriter writer = PdfWriter.getInstance(tempDocument, new FileOutputStream(pdfFile.toString()));
            Font header = FontFactory
                    .getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_HEADER_SIZE, Font.BOLD, BaseColor.BLACK);
            Font text = FontFactory.getFont(CommonFormat.FONT_NAME, CommonFormat.FONT_SIZE, BaseColor.BLACK);
            tempDocument.open();
            if (noteArrayList.isEmpty()) {
                Paragraph title = new Paragraph(NO_DATA, header);
                Paragraph content = new Paragraph(NO_DATA, text);
                tempDocument.add(title);
                tempDocument.add(content);
                tempDocument.close();
                writer.close();
                return;
            }
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
            throw new InvalidFileException(Messages.ERROR_MISSING_FILE);
        } catch (Exception e) {
            throw new InvalidFileException(e.getMessage());
        }
    }

    private String appendFileExtension(String name) {
        return name + FILE_EXTENSION;
    }
}
