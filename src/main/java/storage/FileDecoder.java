package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;

public class FileDecoder extends EncryptedFile {

    public FileDecoder(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        super(fileName);
    }

    public String decodeFile() throws DukeFileNotFoundException, DukeCorruptedFileException {
        try {
            FileInputStream rawFile = new FileInputStream(this.filePath);
            byte[] encText = new byte[rawFile.available()];
            rawFile.read(encText);
            rawFile.close();

            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(encText);

            String textDecryptedString = new String(textDecrypted);
            return textDecryptedString;

        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        } catch (BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            generateNewKey();
            throw new DukeCorruptedFileException();
        }
    }
}
