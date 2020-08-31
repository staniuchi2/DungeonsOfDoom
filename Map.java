
/**
 * Reads and contains in memory the map of the game.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {

    private char[][] map; // array of map

    private String mapName;// name of the map

    private int goldRequired;// variable to hold the amount of gold needed to win

    private int map_x_length;//holds the x dimension of the array

    private int map_y_length;// holds the y dimension of the array

    /**
     * empty constructor, have nothing to set when called.
     */

    public Map() {
    }

    /**
     * calls function to load the text file and stores it into an array, sets the variables storing the x length and the y length
     * of a the map.
     * called once at the start of the game.
     *
     * @param file_name the name of the text file to be read.
     */

    protected void map_startup(String file_name) {
        readMap(file_name);
        map_x_length = map[0].length; //  gets how much elements there are in each array.
        map_y_length = map.length;
    }

    /**
     * returns the amount of gold needed to win
     * @return the amount of money player needs to win.
     */

    protected int getGoldRequired() {
        return goldRequired;
    }

    /**
     * returns the x dimension of the map
     * @return the x dimension of the map.
     */

    protected int get_x_dimension() {
        return map_x_length;
    }

    /**
     * returns the y dimension of the map
     * @return the y dimension of the map.
     */

    protected int get_y_dimension() {
        return map_y_length;
    }

    /**
     * gets the map array.
     * @return returns the array of the map.
     */

    protected char[][] getMap() {
        return map;
    }

    /**
     * a method to set a specific position to desired value in array.
     *
     * @param y_index y value position of tile you want to set to value
     * @param x_index x value position of tile you want to set to value
     * @param value   value you wish to write into position.
     */

    protected void setMap(int y_index, int x_index, char value) {
        map[y_index][x_index] = value;
    }

    /**
     * retrieves the name of the map
     * @return returns name of map.
     */

    protected String getMapName() {
        return mapName;
    }

    /**
     * returns the value of specified tile
     *
     * @param y_index y position of tile you want returned
     * @param x_index x position of tile you want returned
     * @return
     */

    protected char check_tile(int y_index, int x_index) {
        return map[y_index][x_index];
    }

    /**
     * method that reads in the contents of map from file and puts into array.
     * additionally stores the name and gold required to win into respective variables.
     * outputs error if file not found.
     *
     * @param map_name file name of the map. e.g. example_map.txt
     */


    protected void readMap(String map_name) {
        try {
            File new_map = new File(map_name);
            Scanner file_scan = new Scanner(new_map);
            mapName = file_scan.nextLine();
            char temp_gold_required = file_scan.nextLine().charAt(4); // the 4th char is the number
            goldRequired = temp_gold_required - '0'; // this converts char to in
            int file_line_index = 0;
            int line_length = 0;
            String file_line;
            String total_file_string = "";
            while (file_scan.hasNextLine()) {   //keeps reading file until there are no more lines left.
                file_line = file_scan.nextLine();
                total_file_string += file_line; // reads the entire file into one string
                line_length = file_line.length(); //length of each line in the map. X dimension essentially.
                file_line_index++;
            }
            int x_index2 = 0;
            map = new char[file_line_index][line_length];
            for (int y_index = 0; y_index < file_line_index; y_index++) {
                for (int x_index = 0; x_index < line_length; x_index++) {
                    map[y_index][x_index] = total_file_string.charAt(x_index2);
                    x_index2++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}