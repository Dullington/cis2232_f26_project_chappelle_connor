package ca.hccis.squash.util;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDAO {

    private final DataSource dataSource;

    public ReportDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<PlayerTournamentStats> getTournamentStandings(
            String tournamentName) throws SQLException {

        List<PlayerTournamentStats> standings = new ArrayList<>();

        String sql = """
            SELECT
                playerName,
                SUM(matchesPlayed) AS matchesPlayed,
                SUM(setsWon) AS setsWon,
                SUM(setsLost) AS setsLost,
                SUM(setsWon + setsLost) AS setsPlayed,
                ROUND(
                    SUM(setsWon) * 100.0 /
                    NULLIF(SUM(setsWon + setsLost), 0), 2
                ) AS setWinPercentage,
                SUM(aces) AS aces,
                SUM(doubleFaults) AS doubleFaults,
                ROUND(AVG(firstServePercentage), 2) AS firstServePercentage,
                SUM(breakPointsWon) AS breakPointsWon,
                SUM(totalPointsWon) AS totalPointsWon
            FROM (
                SELECT
                    CONCAT(player1FirstName, ' ', player1LastName) AS playerName,
                    tournamentName,
                    1 AS matchesPlayed,
                    player1SetsWon AS setsWon,
                    player2SetsWon AS setsLost,
                    player1Aces AS aces,
                    player1DoubleFaults AS doubleFaults,
                    player1FirstServe AS firstServePercentage,
                    player1BreakPointsWon AS breakPointsWon,
                    player1TotalPointsWon AS totalPointsWon
                FROM tennis_match

                UNION ALL

                SELECT
                    CONCAT(player2FirstName, ' ', player2LastName) AS playerName,
                    tournamentName,
                    1 AS matchesPlayed,
                    player2SetsWon AS setsWon,
                    player1SetsWon AS setsLost,
                    player2Aces AS aces,
                    player2DoubleFaults AS doubleFaults,
                    player2FirstServe AS firstServePercentage,
                    player2BreakPointsWon AS breakPointsWon,
                    player2TotalPointsWon AS totalPointsWon
                FROM tennis_match
            ) AS playerStats

            WHERE tournamentName = ?

            GROUP BY playerName
            ORDER BY setWinPercentage DESC, setsWon DESC;
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, tournamentName);

            try (ResultSet results = statement.executeQuery()) {

                while (results.next()) {

                    PlayerTournamentStats player =
                            new PlayerTournamentStats();

                    player.setPlayerName(results.getString("playerName"));
                    player.setMatchesPlayed(results.getInt("matchesPlayed"));
                    player.setSetsWon(results.getInt("setsWon"));
                    player.setSetsLost(results.getInt("setsLost"));
                    player.setSetsPlayed(results.getInt("setsPlayed"));
                    player.setSetWinPercentage(
                            results.getDouble("setWinPercentage"));
                    player.setAces(results.getInt("aces"));
                    player.setDoubleFaults(results.getInt("doubleFaults"));
                    player.setFirstServePercentage(results.getDouble("firstServePercentage"));
                    player.setBreakPointsWon(
                            results.getInt("breakPointsWon"));
                    player.setTotalPointsWon(
                            results.getInt("totalPointsWon"));

                    standings.add(player);
                }
            }
        }

        return standings;
    }
}