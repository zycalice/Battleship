import java.util.Scanner;

/**
 * This is a battleship game runner in the game Battleship
 *
 * @author  Xinyi Li & Yuchen Zhang
 * @version 1.0
 * @since   2020-12-15
 */
public class BattleshipGame {//the methods below are support methods for user inputs
	
	private int count;
	static int gameCount;
	static int bestCount = Integer.MAX_VALUE;

	/**
	 * constructor, keep track of number of games played.
	 */
	BattleshipGame(){
		if (gameCount==0) System.out.println("Welcome to Battleship. " +
				"This is your first game. \n" +
				"Take your best shot!");
		else System.out.println("Welcome to Battleship. " +
				"Your best score has been "+ bestCount + ". Think you can do better this time? \n" +
				"Take your best shot!");
		gameCount++;

	}
    
	/**
     * check if input is an integer, obtained from previous hw's UserInteraction class
     * @param userInput check if the user input is a valid integer
     * @return true if it is a integer, false if it is not an integer
     */
    protected boolean inputIsInteger(String userInput){
        try{ Integer.parseInt(userInput);
            return true;
        } catch(NumberFormatException e){ return false; }
    }


    /**
     * check if the input does not meet requirement, obtained from previous hw's UserInteraction class
     * @param userInput user input in string format
     * @param min minimum requirement for the integer
     * @param max maximum requirement for the integer
     * @return true if input is not valid; false if it is valid/no error
     */
    protected boolean inputIsNotRequiredInteger(String userInput, int min, int max){
        if (inputIsInteger(userInput)){
            int wordLenInput= Integer.parseInt(userInput);
            return (wordLenInput > max || wordLenInput < min);
        }
        return true;
    }


    /**
     * input a valid integer; obtained from previous hw's UserInteraction class
     * @param min minimum requirement for the integer
     * @param max maximum requirement for the integer
     * @return a valid integer as required
     */
    protected int validInteger(int min, int max){
        Scanner scnr = new Scanner(System.in);
        String userInputS = scnr.nextLine();

        // create a boolean to check for all possible errors
        while(inputIsNotRequiredInteger(userInputS, min, max)) {
            System.out.println("Invalid integer; try again according to the requirement.");
            userInputS = scnr.nextLine();
        }

        return Integer.parseInt(userInputS);
    }


	/**
	 * method to play the game
	 */
	public void play() {
    	Ocean ocean = new Ocean();
    	ocean.placeAllShipsRandomly();

    	while (!ocean.isGameOver()) {
    		//print game
    		ocean.print();
    		//ask for row column input while ensure
			System.out.println("Please enter the row number and column number to shoot at (0-9).");
			System.out.println("Row number:");
			int row = validInteger(0,9);
			System.out.println("Column number:");
			int column = validInteger(0,9);

			//update game
    		if (ocean.shootAt(row, column)) {
    			if (ocean.getShipArray()[row][column].isSunk()) {
    				System.out.println("You just hit and sank a "+
							ocean.getShipArray()[row][column].getShipType());
    			} else {
    				System.out.println("You just hit!");
    			}
    			
    		} else {
    			System.out.println("You just missed!");
    		}
    		count++;
    	}
    	bestCount = Math.min(count, bestCount);
    	ocean.print();
    	System.out.println("Game over! Your final score is: " + count);
		System.out.println("The best possible score is 20.");

    }

	/**
	 * main method
	 * @param args arguments
	 */
	public static void main(String[] args) {
		//while loop to play again
    	String again = "y";
    	while (again.equals("y")) {
    		//first game
			BattleshipGame bsg = new BattleshipGame();
    		bsg.play();
    		//ask for play again
    		System.out.println("Do you want to play again? Your current best score is "+
					BattleshipGame.bestCount+". \nEnter 'y' to continue, other keys to quit");
    		Scanner s = new Scanner(System.in);
    		again = s.nextLine();
    	}
    	
    }

}
