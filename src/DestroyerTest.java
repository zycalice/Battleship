import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DestroyerTest {
	Destroyer destroyer;
	@BeforeEach
	void setUp() throws Exception {
		destroyer = new Destroyer();
	}

	@Test
	void testGetLength() {
		assertEquals(2,destroyer.getLength());
	}

	@Test
	void testGetShipType() {
		assertEquals("destroyer",destroyer.getShipType());
	}

	@Test
	void testGetBowRow() {
		destroyer.setBowRow(5);
		assertEquals(5,destroyer.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		destroyer.setBowColumn(5);
		assertEquals(5,destroyer.getBowColumn());
	}

	@Test
	void testIsHorizontal() {
		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());
	}

	@Test
	void testSetBowRow() {
		destroyer.setBowRow(5);
		assertEquals(5,destroyer.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		destroyer.setBowColumn(5);
		assertEquals(5,destroyer.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());
	}

	@Test
	//true case for okToPlaceShipAt
	void testOkToPlaceShipAtTrue() {
		Ocean ocean = new Ocean();
		assertTrue(destroyer.okToPlaceShipAt(1, 1, false, ocean));
	}
	
	@Test
	//exception case for okToPlaceShipAt
	void testOkToPlaceShipAtRowException() {
		Ocean ocean = new Ocean();
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			destroyer.okToPlaceShipAt(-1, 1, false, ocean);
		});
		assertEquals("Row position of bow cannot be negative", e.getMessage());
	}
	
	@Test
	//exception case for okToPlaceShipAt
	void testOkToPlaceShipAtColException() {
		Ocean ocean = new Ocean();
		Exception e = assertThrows(IllegalArgumentException.class, () -> {
			destroyer.okToPlaceShipAt(1, -1, false, ocean);
		});
		assertEquals("Column position of bow cannot be negative", e.getMessage());
	}
	
	@Test
	//exception false cases for okToPlaceShipAt
	void testOkToPlaceShipAtFalse() {
		Ocean ocean = new Ocean();
		assertFalse(destroyer.okToPlaceShipAt(2, 9, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(9, 2, false, ocean));
		
		destroyer.placeShipAt(3, 3, true, ocean);
		assertFalse(destroyer.okToPlaceShipAt(2,1, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(3,1, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(4,1, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2,5, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(3,5, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(4,5, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2,3, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2,3, false, ocean));
		assertFalse(destroyer.okToPlaceShipAt(3,3, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(4,3, false, ocean));
	}
	
	@Test
	void testPlaceShipAtHorizontal() {
		Ocean ocean = new Ocean();
		destroyer.placeShipAt(1, 1, true, ocean);
		assertTrue(ocean.isOccupied(1, 1));
		assertTrue(ocean.isOccupied(1, 2));
	}
	
	@Test
	void testPlaceShipAtVertical() {
		Ocean ocean = new Ocean();
		destroyer.placeShipAt(1, 1, false, ocean);
		assertTrue(ocean.isOccupied(1, 1));
		assertTrue(ocean.isOccupied(2, 1));
	}
	
	@Test
	void testShootAtSunk() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		des2.shootAt(1, 1);
		des2.shootAt(1, 2);
		assertFalse(des2.shootAt(1, 1));
	}

	@Test
	void testShootAtFalseHorizontal() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		assertFalse(des2.shootAt(1, 0));
		assertFalse(des2.shootAt(1, 3));
		assertFalse(des2.shootAt(0, 1));
		assertFalse(des2.shootAt(2, 1));
		assertFalse(des2.shootAt(5, 5));
	}
	
	@Test
	void testShootAtFalseVertical() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, false, ocean);
		assertFalse(des2.shootAt(0, 1));
		assertFalse(des2.shootAt(3, 1));
		assertFalse(des2.shootAt(1, 0));
		assertFalse(des2.shootAt(1, 2));
		assertFalse(des2.shootAt(5, 5));
	}
	
	@Test
	void testShootAtTrueHorizontal() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		assertTrue(des2.shootAt(1, 1));
		assertTrue(des2.shootAt(1, 2));
	}
	
	@Test
	void testShootAtTrueVertical() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, false, ocean);
		assertTrue(des2.shootAt(1, 1));
		assertTrue(des2.shootAt(2, 1));
	}
	
	@Test
	void testIsSunkTrue() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		des2.shootAt(1, 1);
		des2.shootAt(1, 2);
		assertTrue(des2.isSunk());
	}
	
	@Test
	void testIsSunkFalse() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		des2.shootAt(1, 1);
		assertFalse(des2.isSunk());
	}

	@Test
	void testToStringNotSunk() {
		Destroyer des2 = new Destroyer();
		assertEquals("S",des2.toString());	
	}
	
	@Test
	void testToStringSunk() {
		Destroyer des2 = new Destroyer();
		Ocean ocean = new Ocean();
		des2.placeShipAt(1, 1, true, ocean);
		des2.shootAt(1, 1);
		des2.shootAt(1, 2);
		assertEquals("x",des2.toString());	
	}

}
