package tictactoe.game.guess;

import org.junit.Test;

import static org.junit.Assert.*;

public class GuessValidatorTest {

    @Test
    public void handleValidInput() {
        GuessValidator validator = new GuessValidator(0, 8).validate("8");

        assertTrue("isValid should be true", validator.isValid());
        assertEquals("Guess status should be valid", GuessStatus.Valid, validator.getStatus());
    }

    @Test
    public void retrieveParsedInput() {
       int validatorResult = new GuessValidator(0, 8)
               .validate("8")
               .getValue();

       assertEquals("Guess should have correct value", 8, validatorResult);
    }

    @Test
    public void handleOutOfBounds() {
        GuessValidator validator = new GuessValidator(0, 8).validate("9");

        assertFalse("isValid should be false", validator.isValid());
        assertEquals("Guess should be out of bounds", GuessStatus.OutOfBounds, validator.getStatus());
    }

    @Test
    public void handleUnrecognized() {
        GuessValidator validator = new GuessValidator(0, 8).validate("wut?");

        assertFalse("isValid should be false", validator.isValid());
        assertEquals("Guess should be unrecognized", GuessStatus.Unrecognized, validator.getStatus());
    }
}
