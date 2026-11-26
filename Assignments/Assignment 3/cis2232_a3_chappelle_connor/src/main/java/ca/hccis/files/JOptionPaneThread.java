package ca.hccis.files;

import ca.hccis.files.entity.Tennis;
import ca.hccis.files.bo.TennisBO;
import javax.swing.JOptionPane;


public class JOptionPaneThread implements Runnable {

    @Override
    public void run() {
        JOptionPane.showMessageDialog(
                null,
                "JOptionPane thread started!"
        );

        String menuOption;

        do {
            menuOption = JOptionPane.showInputDialog(
                    null,
                    "A) Add Player\n"
                            + "V) View Players\n"
                            + "X) Exit\n\n"
                            + "Enter your choice:"
            );

            if (menuOption == null) {
                break;
            }

            switch (menuOption.toUpperCase()) {
                case "A":
                    addPlayer();
                    break;

                case "V":
                    viewPlayers();
                    break;

                case "X":
                    JOptionPane.showMessageDialog(
                            null,
                            "JOptionPane thread finished."
                    );
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid menu option."
                    );
                    break;
            }

        } while (!menuOption.equalsIgnoreCase("X"));
    }
    private void addPlayer() {
        Tennis player = new Tennis();
        player.setId(TennisData.getNextId());
        player.getInformationJOptionPane();

        double winRate = new TennisBO().calculate(player);

        TennisData.addPlayer(player);

        JOptionPane.showMessageDialog(
                null,
                "Player added successfully!\n"
                        + "Win rate: " + winRate + "%\n"
                        + "Total players: " + TennisData.getPlayerCount()
        );
    }

    private void viewPlayers() {
        StringBuilder output = new StringBuilder("--- All Players ---\n\n");
        TennisBO tennisBO = new TennisBO();

        for (Tennis player : TennisData.getPlayers().values()) {
            output.append(player)
                    .append("\nWin rate: ")
                    .append(tennisBO.calculate(player))
                    .append("%\n\n");
        }

        output.append("Total players: ")
                .append(TennisData.getPlayerCount());

        JOptionPane.showMessageDialog(null, output.toString());
    }
}