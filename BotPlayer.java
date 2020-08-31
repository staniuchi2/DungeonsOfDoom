
import java.util.Random;

/**
 * inherits superclass Player. Contains information regarding BOT player.
 */
public class BotPlayer extends Player {
    private int bot_x_position;// x position for bot
    private int bot_y_position;// y position for bot
    private char bot_previous_value; // bots previous value
    private char[] possible_directions = {'N','E','S','W'}; // array with possible random directions the bot can choose from.
    Random rand = new Random();


    /**
     * empty constructor.
     */
    protected BotPlayer(){
    }

    /**
     * move method for the bot. Collects a random direction from the array of possible directions and
     * sends it to the main move function in GameLogic.
     */


    protected void Bot_move(){
        System.out.println("Bot turn to move...");
        char bot_direction = possible_directions[rand.nextInt(possible_directions.length)];// selects a random element from the array,
        GameLogic.getLogic().move(Character.toUpperCase(bot_direction));// sends the random direction to the move function.
        GameLogic.getLogic().toggle_turn();// switches the turn back to the human player.
    }

    /**
     * calls method from super class to set a starting position for bot player.
     * sets the starting previous value.
     * sets the starting position to 'B'
     */
    protected void BotPlayer_setup(){
        int[] starting_position_value = starting_position();
        setBot_position(starting_position_value[0],starting_position_value[1]);
        setBot_previous_value(GameLogic.getLogic().getMap().check_tile(getBot_y_position(),getBot_x_position()));
        GameLogic.getLogic().getMap().setMap(getBot_y_position(),getBot_x_position(), 'B');
    }

    /**
     * @return previous value for bot player.
     */

    protected char getBot_previous_value(){
        return bot_previous_value;
    }

    /**
     * method to change previous bot value.
     * @param new_previous new previous value for bot
     */

    protected void setBot_previous_value(char new_previous){
        bot_previous_value = new_previous;
    }

    /**
     * sets new coordinates for bot.
     * @param y_position new y position to set for bot
     * @param x_position new x position to set for bot
     */

    protected void setBot_position(int y_position, int x_position){
        bot_y_position = y_position;
        bot_x_position = x_position;
    }

    /**
     * returns x position for bot
     * @return return x integer position for bot
     */

    protected int getBot_x_position(){
        return bot_x_position;
    }

    /**
     * returns y position for bot
     * @return return y integer position for bot
     */

    protected int getBot_y_position(){
        return bot_y_position;
    }
}
