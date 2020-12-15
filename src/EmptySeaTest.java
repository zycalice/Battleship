import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmptySeaTest {
	EmptySea sea;
	
	@BeforeEach
	void setUp() throws Exception {
		sea = new EmptySea();
	}

	@Test
	void testGetLength() {
		assertEquals(1,sea.getLength());
	}

	@Test
	void testGetShipType() {
		assertEquals("empty",sea.getShipType());
	}

	@Test
	void testShootAt() {
		assertFalse(sea.shootAt(1, 1));
	}

	@Test
	void testIsSunk() {
		assertFalse(sea.isSunk());
	}

	@Test
	void testToString() {
		assertEquals("empty",sea.toString());
	}

}
