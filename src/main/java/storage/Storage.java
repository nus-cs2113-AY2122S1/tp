package storage;

import data.Player;
import data.card.Card;
import data.card.CardContent;
import data.card.CardManager;
import utils.Errors;
import utils.IO;
import utils.message.Strings;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Storage/load feature for the 'player' database.
 *
 * @author Wang Ruisi
 */

public class Storage {

    /**
     * Load player previous save state.
     */
    @SuppressWarnings("unchecked")
    public static void loadPlayer(String playerId) {
        Player.PLAYER_FILE_NAME = playerId + ".dat";
        Object[] playerMember;
        Path saveData = Paths.get(Player.DATAPATH.toString(), Player.PLAYER_FILE_NAME);
        FileInputStream fis;
        ObjectInputStream ois;

        try {
            fis = new FileInputStream(saveData.toString());
            ois = new ObjectInputStream(fis);
            playerMember = (Object[]) ois.readObject();
            if (playerMember != null) {
                Player.setName((String) playerMember[0]);
                Player.setEasyRecords((ArrayList<String>) playerMember[1]);
                Player.setDifficultRecords((ArrayList<String>) playerMember[2]);
                Player.setCardsCollected((CardManager) playerMember[3]);
                Player.setCardsToBeCollected((CardManager) playerMember[4]);
            }
            ois.close();
        } catch (IOException ex) {
            Errors.print(Player.PLAYER_FILE_NAME + Strings.ERR_PLAYER_FILE_NOTFOUND_MESSAGE);
            initPlayer();
        } catch (ClassCastException | ClassNotFoundException ex) {
            System.out.printf((Strings.ERR_PLAYER_FILE_CORRUPTED_MESSAGE) + "%n", Player.PLAYER_FILE_NAME);
            initPlayer();
        }

    }

    /**
     * Storage player data for next time use.
     */
    public static void savePlayer() {
        if (!Files.exists(Player.DATAPATH)) {
            System.out.println("Data folder not found!");
            File dir = new File(Player.DATAPATH.toString());
            if (dir.mkdir()) {
                System.out.println("Directory " + Player.DATAPATH + " created...");
            }
        }

        Object[] playerMember = {Player.getName(), Player.getEasyRecords(), Player.getDifficultRecords(),
            Player.getCardsCollected(), Player.getCardsToBeCollected()};

        Path saveFileName = Paths.get(Player.DATAPATH.toString(), Player.PLAYER_FILE_NAME);
        FileOutputStream fos;
        ObjectOutputStream oos;

        try {
            fos = new FileOutputStream(saveFileName.toString());
            oos = new ObjectOutputStream(fos);
            oos.writeObject(playerMember);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Initialise player static members.
     */
    public static void initPlayer() {
        //initial name
        Scanner sc = new Scanner(System.in);
        String name = IO.readString(sc, Strings.PLAYER_NAME_ENTER_PROMPT).trim();
        assert name != null : "Nothing is inputted!!";
        Player.setName(name);
        String playerId = name + Calendar.getInstance().hashCode();
        Player.PLAYER_FILE_NAME = playerId + ".dat";
        System.out.printf((Strings.PLAYER_ID_PROMPT) + "%n", playerId);

        initEasyGameRecords();
        initDifficultGameRecords();
        initCards();
        System.out.println(Strings.GAME_RECORD_LOADING);
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Overload of  loadPlayer().
     */
    public static void loadPlayer() {
        initPlayer();
    }

    /**
     * Initialise easy game records.
     */
    public static void initEasyGameRecords() {
        Player.setEasyRecords(new ArrayList<>());
    }

    /**
     * Initialise difficult game records.
     */
    public static void initDifficultGameRecords() {
        Player.setDifficultRecords(new ArrayList<>());
    }

    /**
     * Initialise cards. Cards contents are retrieved from data.card.CardContent.
     */
    public static void initCards() {
        ArrayList<Card> cardsInit = CardContent.initCards();
        Player.setCardsCollected(new CardManager());
        Player.setCardsToBeCollected(new CardManager(cardsInit));
    }
}
