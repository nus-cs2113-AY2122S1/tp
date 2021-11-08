package storage;

import exceptions.DukeCorruptedFileException;
import exceptions.DukeFileNotFoundException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class EncryptedFile {
    String fileName;
    String filePath;
    String keyPath = "data/key.txt";
    String direName = "data/";

    static KeyGenerator keygenerator;
    static SecretKey myDesKey;
    static Cipher desCipher;

    public EncryptedFile(String fileName) throws DukeFileNotFoundException, DukeCorruptedFileException {
        this.fileName = fileName;
        this.filePath = direName + this.fileName;
        if (keygenerator == null) {
            initialise();
        }
        checkPath();
    }

    /**
     * Reads the key from text file from keyPath. Generates new key if the current key is corrupted.
     *
     * @throws DukeCorruptedFileException If the file is corrupted.
     * @throws DukeFileNotFoundException If the file is missing.
     */
    public void initialise() throws DukeCorruptedFileException, DukeFileNotFoundException {
        try {
            FileInputStream keyFis = new FileInputStream(keyPath);
            byte[] encKey = new byte[keyFis.available()];
            keyFis.read(encKey);
            keyFis.close();
            keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = new SecretKeySpec(encKey, "DES");
            desCipher = Cipher.getInstance("DES");

        } catch (FileNotFoundException | IllegalArgumentException e) {
            generateNewKey();
        } catch (IOException e) {
            generateNewKey();
            throw new DukeFileNotFoundException();
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            generateNewKey();
            throw new DukeCorruptedFileException();
        }
    }


    public void generateNewKey() throws DukeCorruptedFileException,DukeFileNotFoundException {
        try {
            keygenerator = KeyGenerator.getInstance("DES");
            myDesKey = keygenerator.generateKey();
            desCipher = Cipher.getInstance("DES");

            checkPath();
            byte[] keyAsByte = myDesKey.getEncoded();
            FileOutputStream keyfos = new FileOutputStream(keyPath);
            keyfos.write(keyAsByte);
            keyfos.close();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new DukeCorruptedFileException();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    /**
     * Checks the path to crucial files and creates new file or directory if the path is not found.
     *
     * @throws DukeFileNotFoundException If the file is missing.
     */
    public void checkPath() throws DukeFileNotFoundException {
        try {
            new File(direName).mkdir();
            new File(keyPath).createNewFile();
            new File(filePath).createNewFile();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}









