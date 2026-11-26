package ca.hccis.files;

import ca.hccis.files.entity.Tennis;
import ca.hccis.files.util.CisUtility;
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Controls the overall flow of the program.
 *
 *
 * @author cis2232
 * @since 20260917
 */
public class Controller {

    public static final String EXIT = "X";

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + "X) Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    private static HashMap<Integer, Tennis> playerMap = new HashMap();
    private static Gson gson = new Gson();

    public static final String PATH_NAME = "c:\\cis2232\\players.json";

    public static void main(String[] args) {
        // Load existing player data before either thread starts.
        TennisData.initialize();

        // Create both independent threads.
        Thread consoleThread = new Thread(new ConsoleThread());
        Thread dialogThread = new Thread(new JOptionPaneThread());

        // Start both interfaces.
        consoleThread.start();
        dialogThread.start();
    }

    /**
     * Processing for menu option 1
     *
     * @author
     * @since
     */
    public static void add() {
        Tennis newPlayer = new Tennis();
        IO.println("--Add Player--");
        newPlayer.getInformation();

        //TODO what if the registration id already exists.  Give the user a warning and ask if they want to overwrite
        //the row.
        //read file nad see if the new camper is already there, and if so check with user to see if should overwrite
        playerMap.put(newPlayer.getSetsPlayed(), newPlayer);
        writeAll();
    }

    /**
     * Processing for menu option 2.
     *
     * @author
     * @since
     */
    public static void edit() {
        System.out.println("Processing option 2");
        int regID = CisUtility.getInputInt("Reg ID: ");
        Tennis editingCamper = playerMap.get(regID);
        editingCamper.edit();
        //TODO What if the regID not found?
        //Handle this situation.
        writeAll(); //save to file
    }

    /**
     * Processing for menu option 3.
     *
     * @author
     * @since
     */
    public static void viewAll() {
        readAll();

        for (Tennis player : playerMap.values()) {
            System.out.println(player);
        }
    }


    public static void writeAll() {
        try {
            FileWriter writer = new FileWriter(PATH_NAME, false);
            for (Tennis current : playerMap.values()) {
                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());
                System.out.println("Successfully written JSON string to file.");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAll() {
        try {
            FileReader reader = new FileReader(PATH_NAME);
            List<String> lines = reader.readAllLines();

            for (int i = 0; i < lines.size(); i++) {
                Tennis playerFromJson = gson.fromJson(lines.get(i), Tennis.class);
                playerMap.put(playerFromJson.getId(), playerFromJson);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initialize() {

        Path directory = Paths.get("c:\\cis2232");

        if (!Files.exists(directory)) {
            try {
                Files.createDirectory(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path path = Paths.get(PATH_NAME);

        // Check if the file exists
        if (Files.exists(path)) {
            System.out.println("Players exist.");
            readAll();
        } else {


            Tennis player = new Tennis(1, 12, "Bob", "Stephens", "2020-01-05", 3);
            Tennis player2 = new Tennis(2, 13, "Alice", "Johnson", "2019-07-14", 11);
            Tennis player3 = new Tennis(3, 13, "Charlie", "Williams", "2021-03-22",9);
            Tennis player4 = new Tennis(4, 14, "Diana", "Brown", "2020-11-09",6);
            Tennis player5 = new Tennis(5, 11, "Ethan", "Miller", "2018-05-17",8);
            playerMap.put(player.getSetsPlayed(), player);
            playerMap.put(player2.getSetsPlayed(), player2);
            playerMap.put(player3.getSetsPlayed(), player3);
            playerMap.put(player4.getSetsPlayed(), player4);
            playerMap.put(player5.getSetsPlayed(), player5);

            writeAll();
        }

    }
}
