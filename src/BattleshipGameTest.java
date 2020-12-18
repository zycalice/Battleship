import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleshipGameTest {

    BattleshipGame bg;

    @BeforeEach
    void setUp() throws Exception {
        bg = new BattleshipGame();
    }

    @Test
    void testInputIsIntegerYes() {
        String test = "100";
        assertTrue(bg.inputIsInteger(test));
    }

    @Test
    void testInputIsIntegerNo() {
        String test = "#%^G";
        assertFalse(bg.inputIsInteger(test));
    }

    @Test
    void testInputIsNotRequiredIntegerYes() {
        assertTrue(bg.inputIsNotRequiredInteger("1", 20, 100));
    }


    @Test
    void testInputIsNotRequiredIntegerNo() {
        assertFalse(bg.inputIsNotRequiredInteger("21", 20, 100));
    }

    @Test
    void testInputIsNotRequiredIntegerYes2() {
        assertTrue(bg.inputIsNotRequiredInteger("150", 20, 100));
    }

    @Test
    void testInputIsNotRequiredIntegerYes3() {
        assertTrue(bg.inputIsNotRequiredInteger("$%F", 20, 100));
    }

}