import java.util.Scanner;


/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {
    private static GameLogic logic; // logic object
    private boolean Human_turn;//determines whos turn it is. Boolean value will be true if it is the humans turn.
    private Map map;//map object variable.
    private HumanPlayer HPlayer;//human player object variable
    private BotPlayer BPlayer;//bot player object variable.

    /**
     * creates objects for all the classes. Also sets the Human_turn to true, meaning the human player
     * starts first.
     */
    public GameLogic() {
        map = new Map();
        HPlayer = new HumanPlayer();
        BPlayer = new BotPlayer();
        Human_turn = true;
    }

    /**
     * returns the map object of the class map.
     * @return the object containing the class Map
     */
    public Map getMap(){
        return map;
    }

    /**
     * returns the object of the class GameLogic
     * @return the object containing the class GameLogic
     */
    public static GameLogic getLogic(){
        return logic;
    }

    /**
     * runs all 3 setup functions, called only once at the start.
     */
    protected void game_startup(){
        map.map_startup(mapChoose());
        HPlayer.HumanPlayer_setup();
        BPlayer.BotPlayer_setup();
    }

    /**
     * prompts user to pick difficulty, map changes depending on difficulty chosen.
     *
     * @return map file name.
     */
    protected String mapChoose() {
        boolean valid_difficulty = false;
        String difficulty_chosen = "";
        while (valid_difficulty == false) { // while ensures program keeps prompting for input till valid
            Scanner difficulty_scan = new Scanner(System.in);
            System.out.println("enter difficulty - easy, medium, hard");
            difficulty_chosen = difficulty_scan.nextLine();
            if (difficulty_chosen.equals("easy") || difficulty_chosen.equals("medium") || difficulty_chosen.equals("hard")){
                valid_difficulty = true; // ends loop if one of the difficulties chosen
            }
            else{
                System.out.println("enter valid input");
            }
        }
        if (difficulty_chosen.equals("easy")){
            difficulty_chosen = "small_map.txt"; // size of map chosen based on difficulty
        }
        else if (difficulty_chosen.equals("medium")){
            difficulty_chosen = "medium_map.txt";
        }
        else if (difficulty_chosen.equals("hard")){
            difficulty_chosen = "large_map.txt";
        }
        return difficulty_chosen;
    }

    /**
     * Returns the gold required to win.
     *
     * @return : Gold required to win.
     */
    protected int hello() {
        return map.getGoldRequired();
    }

    /**
     * Returns the gold currently owned by the player.
     *
     * @return : Gold currently owned.
     */
    protected int gold() {
        return HPlayer.get_current_gold_amount();
    }

    /**
     * Checks if the move in the specified direction is possible or not.
     * If it is then just simply increment the respective x or y position value and change value at the spot to the players Character.
     * @param direction : The direction of the movement. Either N,E,S,W.
     * @return : Protocol if success or not.
     */
    protected String move(char direction) {
        int x_position, y_position;
        if (Human_turn) { // depending on whos turn it is, x and y position taken is different.
            x_position = HPlayer.getHuman_x_position(); //human x and y taken
            y_position = HPlayer.getHuman_y_position();
        }
        else{
            x_position = BPlayer.getBot_x_position();// bot x and y taken
            y_position = BPlayer.getBot_y_position();
        }
        if (direction == 'N') {
            char next_tile_value = map.check_tile(y_position - 1, x_position); // check tile returns tile value 1 space north to current position.
            if (next_tile_value != '#'){
                change_previous_value(x_position, y_position, next_tile_value);
                y_position -= 1;
                if (Human_turn){// if human turn then set new tile value to P
                    map.setMap(y_position, x_position, 'P');
                    HPlayer.setHuman_position(y_position, x_position); // set new position for the human
                }
                else { // if bot turn then set to B
                    map.setMap(y_position, x_position, 'B');
                    BPlayer.setBot_position(y_position, x_position);  //  set new position for the bot
                }
                return "SUCCESS";
            }
            else{
                return "FAIL";
            }
        }
        else if (direction == 'E'){
            char next_tile_value = map.check_tile(y_position, x_position + 1);
            if (next_tile_value != '#'){
                change_previous_value(x_position, y_position, next_tile_value);
                x_position += 1;
                if (Human_turn){
                    map.setMap(y_position, x_position, 'P');
                    HPlayer.setHuman_position(y_position, x_position);
                }
                else {
                    map.setMap(y_position, x_position, 'B');
                    BPlayer.setBot_position(y_position, x_position);
                }

                return "SUCCESS";
            }
            else{
                return "FAIL";
            }
        }
        else if (direction == 'S'){
            char next_tile_value = map.check_tile(y_position + 1, x_position);
            if (next_tile_value != '#'){
                change_previous_value(x_position, y_position, next_tile_value);
                y_position += 1;
                if (Human_turn){
                    map.setMap(y_position, x_position, 'P');
                    HPlayer.setHuman_position(y_position, x_position);
                }
                else {
                    map.setMap(y_position, x_position, 'B');
                    BPlayer.setBot_position(y_position, x_position);
                }
                return "SUCCESS";
            }
            else{
                return "FAIL";
            }
        }
        else if (direction == 'W'){
            char next_tile_value = map.check_tile(y_position, x_position - 1);
            if (next_tile_value != '#'){
                change_previous_value(x_position, y_position, next_tile_value);
                x_position -= 1;
                if (Human_turn){
                    map.setMap(y_position, x_position, 'P');
                    HPlayer.setHuman_position(y_position, x_position);
                }
                else {
                    map.setMap(y_position, x_position, 'B');
                    BPlayer.setBot_position(y_position, x_position);
                }
                return "SUCCESS";
            }
            else{
                return "FAIL";
            }
        }
        else{
            System.out.println("invalid ");
        }
        return null;
    }

    /** ca
     * stores the value of the tile before player moves onto it, in the variable previous value.
     * this is so when the player moves off it can be replaced back to normal.
     * @param x_position x coordinate of where the player wants to move
     * @param y_position y coordinate of where the player wants to move
     * @param next_tile_value the value of the tile where the player wants to move.
     */
    protected void change_previous_value(int x_position, int y_position, char next_tile_value){
        if (Human_turn) { // depending on whos turn it is, access different previous values.
            map.setMap(y_position, x_position, HPlayer.getHuman_previous_value());
        }
        else{
            map.setMap(y_position, x_position, BPlayer.getBot_previous_value());
        }
        if (next_tile_value == '.'){
            if (Human_turn) {
                HPlayer.setHuman_previous_value('.');
            }
            else{
                BPlayer.setBot_previous_value('.');
            }
        }
        else if (next_tile_value == 'G'){
            if (Human_turn) {
                HPlayer.setHuman_previous_value('G');
            }
            else{
                BPlayer.setBot_previous_value('G');
            }
        }
        else if(next_tile_value == 'E') {
            if (Human_turn) {
                HPlayer.setHuman_previous_value('E');
            }
            else{
                BPlayer.setBot_previous_value('E');
            }
        }
        else if (next_tile_value == 'B') {
            HPlayer.setHuman_previous_value('B');
            quitGame();
        }
        else if (next_tile_value == 'P'){
            BPlayer.setBot_previous_value('P');
        }
    }

    /**
     * toggles boolean value Human_turn, simply used to change turns.
     */
    protected void toggle_turn(){
        Human_turn = !Human_turn;
    }

    /**
     * prints out a 5 by 5 grid showing the map around the player. Visible areas outside of the map are shown as a wall
     *
     */
    protected void look() {
        //System.out.println(Arrays.deepToString(GameLogic.getLogic().getMap().getMap()).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
        String look_output = "";
        for ( int y_index = HPlayer.getHuman_y_position() - 2; y_index < HPlayer.getHuman_y_position()+ 3; y_index++ ) {
            for (int x_index = HPlayer.getHuman_x_position() - 2; x_index < HPlayer.getHuman_x_position() + 3; x_index++) {
                try { //if it tries to print visible areas outside of map, it just goes to catch where it will print a wall.
                    look_output += map.check_tile(y_index, x_index);
                } catch (ArrayIndexOutOfBoundsException exception) {
                    look_output += "#";
                }
            }
            look_output += "\n";
        }
        System.out.println(look_output);


    }

    /**
     * picks up the gold on the players current location.
     * if there is a gold, then increments the gold amount.
     * @return If the player successfully picked-up gold or not.
     */
    protected String pickup() {
        if (HPlayer.getHuman_previous_value() == 'G'){ //makes sure the player is standing on gold .
            HPlayer.setHuman_previous_value('.'); // once picked up the G tile will be replaced by a normal floor.
            HPlayer.increment_gold_amount();
            return "SUCCESS";
        }
        else{
            return "FAIL";
        }
    }

    /**
     * Quits the game, shutting down the application.
     * Displays win if player is on a exit tile and has same or more amount of gold than required
     * Displays loss if the player quits without reaching the gold criteria
     * Or it is also loss if player moves onto the same tile as the bot or vice versa.
     */
    protected void quitGame() {
        if (HPlayer.getHuman_previous_value() == 'E' && gold() >= hello()){ //if player is on Exit tile and has more gold than required
            System.out.println("WIN");
            System.exit(0); // end the code
        }
        else if (HPlayer.getHuman_previous_value()  == 'B' || BPlayer.getBot_previous_value() == 'P'){ //if bot and player interact with each other then game ends
            System.out.println("CAUGHT BY BOT...LOSE");
            System.exit(0);
        }
        else{
            System.out.println("LOSE"); // for when player just enters quit command.
            System.exit(0);
        }

    }

    /**
     * main function, gets command from the player and then calls the appropriate method depending on the functionality the player chose.
     * loops infinitely until user quits.
     * @param args
     */
    public static void main(String[] args) {
        logic = new GameLogic();
        HumanPlayer player = new HumanPlayer();
        logic.game_startup();
        while (true) {
            String user_command = player.getInputFromConsole();
            if (user_command.equals("HELLO")) {
                int gold_required_win = logic.hello();
                System.out.println("Gold to win: " + gold_required_win);
            }
            else if (user_command.equals("GOLD")) {
                int current_amount_gold = logic.gold();
                System.out.println("Gold owned: " + current_amount_gold);
            }
            else if (user_command.equals("LOOK")) {
                logic.look();
            }
            else if (user_command.equals("PICKUP")) {
                String pickup_check = logic.pickup();
                if (pickup_check.equals("SUCCESS")) {
                    System.out.println(pickup_check + ". Gold owned: " + logic.gold());
                }
                else {
                    System.out.println(pickup_check);
                }
            }
            else if (user_command.equals("MOVE N") || user_command.equals("MOVE E") || user_command.equals("MOVE S") || user_command.equals("MOVE W")) {
                String success_fail = logic.move(user_command.charAt(5)); // the direction is the 5 character, so only 5 the character is passed through.
                System.out.println(success_fail);
            }
            else if (user_command.equals("QUIT")){
                logic.quitGame();
            }
            logic.toggle_turn(); // makes it a bot turn.
            logic.BPlayer.Bot_move(); //calls method to move the bot.

        }
    }
}