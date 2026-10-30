package ca.hccis.squash.util;

public class PlayerTournamentStats {

    private String playerName;
    private int matchesPlayed;
    private int setsWon;
    private int setsLost;
    private int setsPlayed;
    private double setWinPercentage;
    private int aces;
    private int doubleFaults;
    private int breakPointsWon;
    private int totalPointsWon;
    private double firstServePercentage;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public int getSetsLost() {
        return setsLost;
    }

    public void setSetsLost(int setsLost) {
        this.setsLost = setsLost;
    }

    public int getSetsPlayed() {
        return setsPlayed;
    }

    public void setSetsPlayed(int setsPlayed) {
        this.setsPlayed = setsPlayed;
    }

    public double getSetWinPercentage() {
        return setWinPercentage;
    }

    public void setSetWinPercentage(double setWinPercentage) {
        this.setWinPercentage = setWinPercentage;
    }

    public int getAces() {
        return aces;
    }

    public void setAces(int aces) {
        this.aces = aces;
    }

    public int getDoubleFaults() {
        return doubleFaults;
    }

    public void setDoubleFaults(int doubleFaults) {
        this.doubleFaults = doubleFaults;
    }

    public int getBreakPointsWon() {
        return breakPointsWon;
    }

    public void setBreakPointsWon(int breakPointsWon) {
        this.breakPointsWon = breakPointsWon;
    }

    public int getTotalPointsWon() {
        return totalPointsWon;
    }

    public void setTotalPointsWon(int totalPointsWon) {
        this.totalPointsWon = totalPointsWon;
    }

    public double getFirstServePercentage() {
        return firstServePercentage;
    }

    public void setFirstServePercentage(double firstServePercentage) {
        this.firstServePercentage = firstServePercentage;
    }
}