import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CruiserTest {
	Cruiser cruiser;
	
	@BeforeEach
	void setUp() throws Exception {
		cruiser = new Cruiser();
	}

	@Test
	void testGetLength() {
		assertEquals(3,cruiser.getLength());
	}

	@Test
	void testGetShipType() {
		assertEquals("cruiser",cruiser.getShipType());
	}

}
