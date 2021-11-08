package data;

import data.game.Game;
import data.card.CardManager;
import data.card.Card;
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
import java.util.List;
import java.util.Scanner;
import java.util.Calendar;

/**
 * A player.
 */
public class Player {
    public static final Path DATAPATH = Paths.get(System.getProperty("user.dir"), "data");
    public static String PLAYER_FILE_NAME;

    /**
     * store the card id of the card collected -- for storage use.
     */
    public static List<Integer> collectedCardIDs = new ArrayList<>();

    /**
     * The name of the restaurant.
     */
    private static String name;

    /**
     * The list of easy games.
     */
    private static List<String> easyRecords = new ArrayList<>();

    /**
     * The list of difficult games.
     */
    private static List<String> difficultRecords = new ArrayList<>();

    /**
     * The used cards list.
     */
    private static CardManager cardsCollected;

    /**
     * The cards list.
     */
    private static CardManager cardsToBeCollected;


    public static String getName() {
        return name;
    }

    public static List<String> getEasyRecords() {
        return easyRecords;
    }

    public static List<String> getDifficultRecords() {
        return difficultRecords;
    }

    public static CardManager getCardsCollected() {
        return cardsCollected;
    }

    public static CardManager getCardsToBeCollected() {
        return cardsToBeCollected;
    }

    public static void setName(String name) {
        Player.name = name;
    }

    public static void setEasyRecords(List<String> easyRecords) {
        Player.easyRecords = easyRecords;
    }

    public static void setDifficultRecords(List<String> difficultRecords) {
        Player.difficultRecords = difficultRecords;
    }

    public static void setCardsCollected(CardManager cardsCollected) {
        Player.cardsCollected = cardsCollected;
    }

    public static void setCardsToBeCollected(CardManager cardsToBeCollected) {
        Player.cardsToBeCollected = cardsToBeCollected;
    }

    /**
     * startID indicates which game's card is collected.
     *
     * @param startID the startID return by the "execute()" method of games, startID = 0 indicates loose
     * @return boolean: if win card successfully
     */
    public static boolean winCard(int startID) {
        if (startID == 0) {
            System.out.println(Strings.NO_CARD_MESSAGE);
            return false;   // if startID = 0 , actually should not enter this method, need to be solved in the menu
        }
        int index = cardsToBeCollected.getCardPosition(startID);
        boolean canBeCollected = index == -1 ? false : true;
        if (canBeCollected) {
            int cardID = cardsToBeCollected.transferTo(cardsCollected, index);
            collectedCardIDs.add(cardID);
            // print card
        } else {
            System.out.println("Oops! You have already collected all the cards for this game.");
        }

        return canBeCollected;   // return false means not enough cards to collect for this game
    }


    private Player(String name, List<String> easyRecords, List<String> difficultRecords,
                   CardManager cardsCollected, CardManager cardsToBeCollected) {
        Player.name = name;
        Player.easyRecords = easyRecords;
        Player.difficultRecords = difficultRecords;
        Player.cardsCollected = cardsCollected;
        Player.cardsToBeCollected = cardsToBeCollected;

    }

    public static void showEasyGameRecord() {
        System.out.println(Strings.SHOW_EASY_PROGRESS);
        if (easyRecords.size() == 0) {
            System.out.println(Strings.NO_GAME_RECORD_MESSAGE);
        } else {
            for (String name : easyRecords) {
                System.out.println(name);
            }
        }
    }

    public static void showDifficultGameRecord() {
        System.out.println(Strings.SHOW_DIFFICULT_PROGRESS);
        if (difficultRecords.size() == 0) {
            System.out.println(Strings.NO_GAME_RECORD_MESSAGE);
        } else {
            for (String name : difficultRecords) {
                System.out.println(name);
            }
        }
    }

    public static String printEasyRecord() {
        int size = easyRecords.size();
        if (size == 0) {
            return "empty";
        } else {
            System.out.println(Strings.GAME_RECORD_LOADING);
            return easyRecords.get(size - 1);
        }
    }

    public static String printDifficultRecord() {
        int size = difficultRecords.size();
        if (size == 0) {
            return "empty";
        } else {
            System.out.println(Strings.GAME_RECORD_LOADING);
            return difficultRecords.get(size - 1);
        }
    }

    public static void addEasyGameRecord(Game easyGame) {
        easyRecords.add(easyGame.getName());
    }

    public static void addDifficultGameRecord(Game difficultGame) {
        difficultRecords.add(difficultGame.getName());
    }

    public static void showCollectedCards() {
        cardsCollected.listCards();
    }

    public static void deleteCard(int index) {
        cardsCollected.deleteCard(index);
    }

    public static void findCard(int cardID) {
        cardsCollected.findPrintCard(cardID);
    }

    public static int getCardSize() {
        return cardsCollected.getSize();
    }


}
