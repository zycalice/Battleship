import java.util.ArrayList;
import java.util.Random;

/**
 * This is a class the ocean, operates the ocean
 *
 * @author  Xinyi Li & Yuchen Zhang
 * @version 1.0
 * @since   2020-12-15
 */
public class Ocean {
    protected Ship[][] ships = new Ship[10][10];
    private int shotsFired = 0;
    private int hitCount = 0;
    protected int shipsSunk = 0;

    /**
     * constructor to create an empty sea
     */
    public Ocean(){
       for (int i=0; i<10; i++){
           for (int j=0;j<10; j++){
               EmptySea empty = new EmptySea();
               ships[i][j] = empty;
           }
       }
    }

    //TODO: verify if we can change separate out the method to initiateShips
    /**
     * place all ships randomly in the ocean
     */
    void placeAllShipsRandomly(){
        ArrayList<Ship> shipArrayList = initiateShips();
        Random random = new Random();
        int row;
        int col;
        boolean orientation;
        //place battleship first
        for (Ship ship: shipArrayList){
            row = random.nextInt(10);
            col = random.nextInt(10);
            orientation =  random.nextBoolean();

            while(!ship.okToPlaceShipAt(row, col, orientation,this)){
                row = random.nextInt(10);
                col = random.nextInt(10);
                orientation =  random.nextBoolean();
            }

            ship.placeShipAt(row,col,orientation,this);
        }

    }

    /**
     * create arraylist of ships
     * @return an arraylist of ships
     */
    ArrayList<Ship> initiateShips(){
        ArrayList<Ship> initialShips = new ArrayList<>();

        //battleship
        for (int i=0;i<1;i++){
            Battleship battleship = new Battleship();
            initialShips.add(battleship);
        }

        //cruiser
        for (int i=0;i<2;i++){
            Cruiser cruiser = new Cruiser();
            initialShips.add(cruiser);
        }

        //destroyer
        for (int i=0;i<3;i++){
            Destroyer destroyer = new Destroyer();
            initialShips.add(destroyer);
        }

        //submarine
        for (int i=0;i<4;i++){
            Submarine submarine =  new Submarine();
            initialShips.add(submarine);
        }

        return initialShips;
    }


    /**
     * check if a position is occupied
     * @param row row of the position
     * @param column column of the position
     * @return return true is the position is occupied; return false if it is empty
     */
    boolean isOccupied(int row, int column){
        return (!ships[row][column].getShipType().equals("empty"));
    }

    /**
     * If a location contains a "real" ship (not an empty ship),
     * shootAt should return true every time the user shoots at that same location.
     * Once a ship has been "sunk", additional shots at its location should return false.
     * @param row row position of the hit
     * @param column column position of the hit
     * @return true everytime the user shoots at the same location if the ship is still up; otherwise false
     */
    boolean shootAt(int row, int column){

        //update shots fired
        //call Ship.shootAt to update the ship
        //check if new ship sunk
        this.shotsFired++;
        boolean prevSunk = ships[row][column].isSunk();

        ships[row][column].shootAt(row, column);
        boolean currSunk = ships[row][column].isSunk();
        if (!prevSunk && currSunk) shipsSunk++;

        //if the location is empty, return false
        if (!isOccupied(row,column)) return false;
        //if the location has a real ship but already sunk
        if (prevSunk) return false;
        //if the location has a real ship an is not sunk yet
        else {
            this.hitCount++;
            return true;
        }

    }

    /**
     * getter for shotsFired
     * @return integer, number of shots fired
     */
    public int getShotsFired(){
        return shotsFired;
    }

    /**
     * getter for hitCount
     * @return integer, number of hits
     */
    public int getHitCount(){
        return hitCount;
    }


    /**
     * getter for shipsSunk
     * @return integer, number of ships sunk
     */
    public int getShipsSunk(){
        return shipsSunk;
    }

    /**
     * check if the game ended, meaning all 10 ships are sunk
     * @return true if game ended, false if game is not
     */
    boolean isGameOver(){
        return (getShipsSunk()==10);
    }

    /**
     * get the ships array
     * @return 2D array of row positions and column positions
     */
    Ship[][] getShipArray(){
        return ships;
    }

    /**
     * print the ocean;
     * 'x' to indicate a location containing a sunken ship (all parts are hit)
     * '-' to indicate a location that user have fired upon and found nothing there
     * 'S' to indicate a location that user have fired upon and hit a (real) ship
     * '.' (a period) to indicate a location that you have never fired upon.
     */
    void print(){
        for (int i=0; i<11; i++){
            for (int j=0; j<11; j++){
                //print 0-9s on the horizontal and vertical axis
                if (i == 0 & j==0) System.out.print("   ");
                else if (i==0) System.out.print(" "+(j-1)+" ");
                else if (j==0) System.out.print(" "+(i-1)+" ");
                else{
                    //print out ships and status
                    Ship ship = ships[i-1][j-1];
                    if(ship.isSunk()) System.out.print(" x ");
                    else if(ship.getShipType().equals("empty") && ship.hit[1]) System.out.print(" - ");
                    else if(ship.getShipType().equals("empty") && !ship.hit[1]) System.out.print(" . ");
                    else if(ship.isHorizontal() && ship.hit[j-1-ship.getBowColumn()]) System.out.print(" S ");
                    else if(ship.isHorizontal() && !ship.hit[j-1-ship.getBowColumn()]) System.out.print(" . ");
                    else if(!ship.isHorizontal() && ship.hit[i-1-ship.getBowRow()]) System.out.print(" S ");
                    else if(!ship.isHorizontal() && !ship.hit[i-1-ship.getBowRow()]) System.out.print(" . ");
                }
            }
            System.out.print("\n");
        }
    }

//    public static void main(String[] args) {
    	/*
        //test to see the placement
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        for (int i=0; i<11; i++){
            for (int j=0; j<11; j++){
                //print 0-9s on the horizontal and vertical axis
                if (i == 0 & j==0) System.out.print("   ");
                else if (i==0) System.out.print(" "+(j-1)+" ");
                else if (j==0) System.out.print(" "+(i-1)+" ");
                else{
                    //print out ships and status
                    Ship ship = ocean.getShipArray()[i-1][j-1];
                    if(ship.getShipType().equals("empty")) System.out.print(" . ");
                    else System.out.print(" " + ship.toString() + " ");
                }
            }
            System.out.print("\n");
        }
		*/
//    }

}
