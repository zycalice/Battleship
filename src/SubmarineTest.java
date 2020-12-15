import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubmarineTest {
	Submarine submarine;
	
	@BeforeEach
	void setUp() throws Exception {
		submarine = new Submarine();
	}

	@Test
	void testGetLength() {
		assertEquals(1,submarine.getLength());
	}

	@Test
	void testGetShipType() {
		assertEquals("submarine",submarine.getShipType());
	}

}
