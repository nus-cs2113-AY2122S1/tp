package scene.suspect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import scene.clue.Clue;
import scene.clue.firstscene.FatherInsurance;
import scene.clue.firstscene.FatherMap;
import scene.clue.firstscene.FatherTextMessage;
import org.junit.jupiter.api.Test;
import scene.clue.secondscene.FatherDiary;
import scene.clue.secondscene.FatherQuarrel;
import scene.clue.secondscene.KevinBro;
import scene.clue.thirdscene.FatherFinancial;
import scene.clue.thirdscene.FatherTofu;
import scene.clue.thirdscene.ZackDrink;
import scene.clue.thirdscene.ZackMemo;

import java.util.ArrayList;

public class SuspectTest {

    @Test
    public void suspectWithTwoCluesTest() {
        Suspect zack = new Suspect();
        Clue zackDrink = new ZackDrink();
        Clue zackMemo = new ZackMemo();

        zack.addClue(zackDrink);
        zack.addClue(zackMemo);
        assertEquals(2, zack.getNumClues());

        ArrayList<Clue> allClues = new ArrayList<>();
        allClues.add(zackDrink);
        allClues.add(zackMemo);
        assertEquals(allClues, zack.getClues());
    }

    @Test
    public void setCluesAsCheckedTest() {
        Suspect father = new Suspect();
        Clue fatherFinancial = new FatherFinancial();
        Clue fatherTofu = new FatherTofu();
        Clue fatherQuarrel = new FatherQuarrel();
        father.addClue(fatherFinancial);
        father.addClue(fatherTofu);
        father.addClue(fatherQuarrel);

        father.setChecked(fatherFinancial);
        assertEquals(2, father.getAvailableClues().size());

        father.setChecked(fatherTofu);
        assertEquals(1, father.getAvailableClues().size());

        father.setChecked(fatherQuarrel);
        assertEquals(0, father.getAvailableClues().size());
    }

    @Test
    public void markClue_ClueNotFound() {
        Suspect father = new Suspect();
        Clue fatherMap = new FatherMap();
        father.addClue(fatherMap);

        Clue kevinBro = new KevinBro();
        father.setChecked(kevinBro);

        Clue fatherTextMessage = new FatherTextMessage();
        father.setChecked(fatherTextMessage);
    }

    @Test
    public void getCheckedCluesTest_TwoClues() {
        Suspect father = new Suspect();
        Clue fatherInsurance = new FatherInsurance();
        Clue fatherDiary = new FatherDiary();

        father.addClue(fatherInsurance);
        father.addClue(fatherDiary);

        father.setChecked(fatherDiary);

        ArrayList<Clue> correctClues = new ArrayList<>();
        correctClues.add(fatherDiary);
        assertEquals(correctClues, father.getCheckedClues());
    }

}
