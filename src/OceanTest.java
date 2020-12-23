import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    void constructorTest(){
        Ocean oceanInit = new Ocean();
        //test the shape of the ship[][]
        assertEquals(10, oceanInit.getShipArray()[0].length);
        assertEquals(10, oceanInit.getShipArray().length);

        //test each ship's type, should be empty
        for (int i=0; i<10;i++){
            for (int j=0; j<10;j++) {
                assertEquals("empty", oceanInit.getShipArray()[i][j].getShipType());
            }
        }
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
        //shoot at a few places
        ocean.shootAt(5,5);
        ocean.shootAt(3,4);
        //test gets shots fired
        assertEquals(2, ocean.getShotsFired());
    }

    @org.junit.jupiter.api.Test
    void getShipsSunkTest() {
        //before shooting
        assertEquals(0, ocean.getShipsSunk());
        //shoot at a submarine
        ocean.shootAt(5,5);
        //after shooting
        assertEquals(1, ocean.getShipsSunk());
    }

    @org.junit.jupiter.api.Test
    void isGameOverYesTest() {
        //set number of ships sunk to 10
        ocean.shipsSunk = 10;
        //check if the game is over after setting that
        assertTrue(ocean.isGameOver());
    }

    @org.junit.jupiter.api.Test
    void isGameOverNoTest() {
        //set number of ships sunk to 5
        ocean.shipsSunk = 5;
        //check if the game is over after setting that
        assertFalse(ocean.isGameOver());
    }

    @org.junit.jupiter.api.Test
    void getShipArrayTest() {
        Ship[][] shipsTest = ocean.getShipArray();
        //check a few positions according to the setup
        assertEquals("empty", shipsTest[1][1].getShipType());
        assertEquals("destroyer", shipsTest[3][4].getShipType());
        assertEquals("destroyer", shipsTest[3][5].getShipType());
        assertEquals("submarine", shipsTest[5][5].getShipType());
    }

    @Test
    void initiateShipTest(){
        ArrayList<Ship> ships = ocean.initiateShips();
        //test size
        assertEquals(10, ships.size());
        //select a few positions for testing
        assertEquals("battleship", ships.get(0).getShipType());
        assertEquals("destroyer", ships.get(5).getShipType());
        assertEquals("submarine", ships.get(9).getShipType());
    }
}