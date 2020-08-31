import java.util.Scanner;

/**
 * inherits super class Player. Contains information regarding HUMAN player.
 */
public class HumanPlayer extends Player{
    private Scanner scan;
    private int current_gold_amount; // amount of gold player has
    private int human_x_position; // x position of human player
    private int human_y_position; // y position of human player
    private char human_previous_value; // previous value stores the value of the tile that the player is currently on.
    // this is important as once the player moves off that tile, it needs to be restored to its previous value.

    /**
     * sets the gold of player to when called.
     * Also creates a new scanner object.
     */

    public HumanPlayer(){
        scan = new Scanner(System.in);
        current_gold_amount = 0;
    }

    /**
     * calls method from super class to set a starting position for human player.
     * sets the starting previous value.
     * sets the starting position to 'P'
     */

    protected void HumanPlayer_setup(){
        int[] starting_position_values = starting_position(); // gets returned the x and y starting position.
        setHuman_position(starting_position_values[0],starting_position_values[1]);
        setHuman_previous_value(GameLogic.getLogic().getMap().check_tile(getHuman_y_position(),getHuman_x_position()));
        GameLogic.getLogic().getMap().setMap(getHuman_y_position(),getHuman_x_position(), 'P');
    }

    /**
     * gets the previous value for Human player
     * @return previous value for human player.
     */

    protected char getHuman_previous_value(){
        return human_previous_value;
    }

    /**
     * method to change previous for human player.
     * @param new_previous previous value.
     */

    protected void setHuman_previous_value( char new_previous){
        human_previous_value= new_previous;
    }

    /**
     * returns the x coordinate for human player
     * @return returns x coordinate for human player.
     */

    protected int getHuman_x_position(){
        return human_x_position;
    }

    /**
     * returns the y coordinate for human player
     * @return returns y coordinate for human player.
     */

    protected int getHuman_y_position(){
        return human_y_position;
    }

    /**
     * sets the new coordinates for human player.
     * @param y_position new y position to set for human
     * @param x_position new x position to set for human
     */

    protected void setHuman_position(int y_position, int x_position){
        human_x_position = x_position;
        human_y_position = y_position;
    }

    /**
     * shows how much gold the player currently has
     * @return the amount of gold the player currently has.
     */

    protected int get_current_gold_amount(){
        return current_gold_amount;
    }

    /**
     * increases the amount of gold the player has by one.
     */

    protected void increment_gold_amount(){
        current_gold_amount += 1;
    }




}