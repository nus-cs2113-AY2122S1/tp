//@@author WU-LUOYU-SERENA

package scene.clue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClueTest {

    @Test
    public void toString_InstantiateFatherMap_printMessages() {
        String expectedResult = "------------------------------------------------\n"
                +  "                     Map\n"
                + "Father's           DNA Testing\n"
                + "company              Agency\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + " 20|                  20|\n"
                + "min|                 min|\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + "Vegetable ____________ Home ____________ Seafood ___________________ Insurance\n"
                + "  Store      5 min      |      5 min      Store         25 min        Company\n"
                + "                        |\n"
                + "                      25|\n"
                + "                     min|\n"
                + "                        |\n"
                + "                        |\n"
                + "                   Money Lender";
        String fatherMapName = "                     Map";
        String fatherMapImage = "Father's           DNA Testing\n"
                + "company              Agency\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + " 20|                  20|\n"
                + "min|                 min|\n"
                + "   |                    |\n"
                + "   |                    |\n"
                + "Vegetable ____________ Home ____________ Seafood ___________________ Insurance\n"
                + "  Store      5 min      |      5 min      Store         25 min        Company\n"
                + "                        |\n"
                + "                      25|\n"
                + "                     min|\n"
                + "                        |\n"
                + "                        |\n"
                + "                   Money Lender";
        String fatherMapDescription = "";
        Clue fatherMap = new Clue(fatherMapName, fatherMapImage, fatherMapDescription);
        String actualResult = fatherMap.toString();
        assertEquals(expectedResult, actualResult);
    }
}
