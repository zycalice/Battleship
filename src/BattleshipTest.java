import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleshipTest {
	Battleship battleship;
	@BeforeEach
	void setUp() throws Exception {
		battleship = new Battleship();
	}

	@Test
	void testGetLength() {
		assertEquals(4,battleship.getLength());
	}

	@Test
	void testGetShipType() {
		assertEquals("battleship",battleship.getShipType());
	}


}
