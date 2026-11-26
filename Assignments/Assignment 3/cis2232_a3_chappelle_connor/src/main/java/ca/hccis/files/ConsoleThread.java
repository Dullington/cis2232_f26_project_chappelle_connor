
package ca.hccis.files;

import ca.hccis.files.util.CisUtility;

public class ConsoleThread implements Runnable {

    private static final String EXIT = "X";

    private static final String MENU =
            "A) Add" + System.lineSeparator()
                    + "V) View" + System.lineSeparator()
                    + "X) Exit" + System.lineSeparator();

    @Override
    public void run() {
        System.out.println("Console thread started!");

        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU);

            switch (menuOption.toUpperCase()) {
                case "A":
                    addPlayer();
                    break;
                case "V":
                    viewPlayers();
                    break;

                case EXIT:
                    System.out.println("Console thread finished.");
                    break;

                default:
                    System.out.println("Invalid menu option.");
                    break;
            }

        } while (!menuOption.equalsIgnoreCase(EXIT));
    }
    private void addPlayer() {
        ca.hccis.files.entity.Tennis player =
                new ca.hccis.files.entity.Tennis();

        player.setId(TennisData.getNextId());
        player.getInformation();

        double winRate =
                new ca.hccis.files.bo.TennisBO().calculate(player);

        TennisData.addPlayer(player);

        System.out.println("Player added successfully!");
        System.out.println("Win rate: " + winRate + "%");
        System.out.println("Total players: " + TennisData.getPlayerCount());
    }

    private void viewPlayers() {
        System.out.println("\n--- All Players ---");

        for (ca.hccis.files.entity.Tennis player
                : TennisData.getPlayers().values()) {
            System.out.println(player);
            System.out.println(
                    "Win rate: "
                            + new ca.hccis.files.bo.TennisBO().calculate(player)
                            + "%");
        }

        System.out.println("Total players: " + TennisData.getPlayerCount());
    }
}
