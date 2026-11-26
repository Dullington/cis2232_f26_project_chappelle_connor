package ca.hccis.files.bo;

import ca.hccis.files.entity.Tennis;

public class TennisBO {

    public double calculate(Tennis tennis) {
        return (double) tennis.getPlayerSetsWon() / tennis.getSetsPlayed() * 100;
    }
}