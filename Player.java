
/**
 * Parent class for HumanPlayer and BotPlayer.
 */

import java.util.Random;
import java.util.Scanner;

public class Player {

    private Scanner scan;//scanner to take in inputs.

    /**
     * nothing to set in constructor, just creates a local scanner object.
     */

    public Player(){
        scan = new Scanner(System.in);
    }

    /**
     * function that gives a prompt to user to enter a command. Once it takes input it checks if it is valid.
     * once it is valid, it is returned back to the main.
     * @return user command.
     */


    protected String getInputFromConsole() {

        boolean valid_input = false;
        String user_command;
        do {
            System.out.println("enter command: ");
            user_command = (scan.nextLine().toUpperCase()); //change all input to uppercase
            if (user_command.equals("HELLO")) {
                valid_input = true;
            }
            else if (user_command.equals("GOLD")) {
                valid_input = true;
            }
            else if (user_command.equals("MOVE N") || user_command.equals("MOVE E") || user_command.equals("MOVE S") || user_command.equals("MOVE W")) {
                valid_input = true;
            }
            else if (user_command.equals("PICKUP")) {
                valid_input = true;
            }
            else if (user_command.equals("LOOK")) {
                valid_input = true;
            }
            else if (user_command.equals("QUIT")) {
                valid_input = true;
            }
            else {
                System.out.println("please enter valid input");
            }
        }
        while (!valid_input);
        return user_command;
    }

    /**
     * Method used to determine a valid starting position for both the bot and player.
     * @return a integer array with both x and y positions of valid starting positions.
     */

    protected int[] starting_position() {
        Random rand = new Random();
        int rand_x = rand.nextInt(GameLogic.getLogic().getMap().get_x_dimension() - 1);
        int rand_y = rand.nextInt(GameLogic.getLogic().getMap().get_y_dimension() - 1);
        while (GameLogic.getLogic().getMap().check_tile(rand_y,rand_x) != '.' && GameLogic.getLogic().getMap().check_tile(rand_y, rand_x) != 'G' && GameLogic.getLogic().getMap().check_tile(rand_y, rand_x) != 'P') {
            rand_x = rand.nextInt( GameLogic.getLogic().getMap().get_x_dimension()- 1);
            rand_y = rand.nextInt( GameLogic.getLogic().getMap().get_y_dimension()- 1);
        }
        return new int[]{rand_y, rand_x};
    }
}
