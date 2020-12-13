import java.util.Scanner;

public class BattleshipGame {//the methods below are support methods for user inputs

    /**
     * check if input is an integer
     * @param userInput check if the user input is a valid integer
     * @return true if it is a integer, false if it is not an integer
     */
    protected boolean inputIsInteger(String userInput){
        try{ Integer.parseInt(userInput);
            return true;
        } catch(NumberFormatException e){ return false; }
    }


    /**
     * check if the input does not meet requirement
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
     *
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

    public static void main(String[] args) {

    }

}
