package terminus.activerecall;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DifficultyModifierTest {

    @Test
    void tweakEasyQuestionDifficulty_lowerThanMinValue_returnMinValue() {
        assertEquals(0.2, DifficultyModifier.tweakEasyQuestionDifficulty(0.1));
        assertEquals(0.2, DifficultyModifier.tweakEasyQuestionDifficulty(0.2));
        assertEquals(0.2, DifficultyModifier.tweakEasyQuestionDifficulty(0));
        assertEquals(0.2, DifficultyModifier.tweakEasyQuestionDifficulty(Double.MIN_VALUE));
    }

    @Test
    void tweakHardQuestionDifficulty_higherThanMaxValue_returnMaxValue() {
        assertEquals(0.9, DifficultyModifier.tweakHardQuestionDifficulty(1));
        assertEquals(0.9, DifficultyModifier.tweakHardQuestionDifficulty(0.9));
        assertEquals(0.9, DifficultyModifier.tweakHardQuestionDifficulty(100000));
        assertEquals(0.9, DifficultyModifier.tweakHardQuestionDifficulty(Double.MAX_VALUE));
    }


}
