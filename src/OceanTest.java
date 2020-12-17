import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class OceanTest {
    Ocean ocean;


    @BeforeEach
    void setUp(){
        ocean = new Ocean();
        Ship destroyer =  new Destroyer();
        Ship submarine = new Submarine();
        destroyer.placeShipAt(3,4,true, ocean);
        submarine.placeShipAt(5,5,false, ocean);
    }

    @org.junit.jupiter.api.Test
    void isOccupiedFalseTest() {
        assertFalse(ocean.isOccupied(3,3));
    }

    @org.junit.jupiter.api.Test
    void isOccupiedTrueTest() {
        assertTrue(ocean.isOccupied(3,4));
    }

    @org.junit.jupiter.api.Test
    void shootAtHitTest() {
        //before shoot
        assertEquals(0,ocean.getHitCount());
        assertEquals(0,ocean.getShotsFired());

        //shoot and hit
        ocean.shootAt(3,4);
        ocean.shootAt(5,5);

        //after shot and hit
        assertEquals(2,ocean.getShotsFired());
        assertEquals(2,ocean.getHitCount());
        assertEquals(1,ocean.getShipsSunk());
        assertTrue(ocean.shootAt(3,4));
    }

    @org.junit.jupiter.api.Test
    void shootAtNotHitTest() {
        //before shoot
        assertEquals(0,ocean.getHitCount());
        assertEquals(0,ocean.getShipsSunk());

        //shoot and not hit
        ocean.shootAt(3,3);

        //after shot and hit
        assertEquals(1,ocean.getShotsFired());
        assertEquals(0,ocean.getHitCount());
        assertEquals(0,ocean.getShipsSunk());
        assertFalse(ocean.shootAt(3,3));
    }

    //TODO not sure if it is a good practice to involve code here but instance vars are private
    @org.junit.jupiter.api.Test
    void getShotsFiredTest() {
        for (int i=0; i<5; i++){
            ocean.shootAt(3,3);
        }
        assertEquals(5, ocean.getShotsFired());
    }

    @org.junit.jupiter.api.Test
    void getHitCountTest() {
        ocean.shootAt(5,5);
        ocean.shootAt(3,4);
        assertEquals(2, ocean.getShotsFired());
    }

    @org.junit.jupiter.api.Test
    void getShipsSunkTest() {
        assertEquals(0, ocean.getShipsSunk());
        ocean.shootAt(5,5);
        assertEquals(1, ocean.getShipsSunk());
    }

    @org.junit.jupiter.api.Test
    void isGameOverYesTest() {
        ocean.shipsSunk = 10;
        assertTrue(ocean.isGameOver());
    }

    @org.junit.jupiter.api.Test
    void isGameOverNoTest() {
        ocean.shipsSunk = 5;
        assertFalse(ocean.isGameOver());
    }

    @org.junit.jupiter.api.Test
    void getShipArrayTest() {
        Ship[][] shipsTest = ocean.getShipArray();
        assertEquals("empty", shipsTest[1][1].getShipType());
        assertEquals("destroyer", shipsTest[3][4].getShipType());
        assertEquals("destroyer", shipsTest[3][5].getShipType());
        assertEquals("submarine", shipsTest[5][5].getShipType());
    }
}