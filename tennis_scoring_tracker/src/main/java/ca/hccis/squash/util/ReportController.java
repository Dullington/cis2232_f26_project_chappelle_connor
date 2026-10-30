package ca.hccis.squash.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ReportController {

    private final ReportDAO reportDAO;

    public ReportController(ReportDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    @GetMapping("/report")
    public String showReportForm() {
        return "report";
    }

    @PostMapping("/report")
    public String generateReport(
            @RequestParam("tournament") String tournament,
            Model model) {

        try {
            List<PlayerTournamentStats> standings =
                    reportDAO.getTournamentStandings(tournament);

            model.addAttribute("tournament", tournament);
            model.addAttribute("standings", standings);

        } catch (SQLException e) {
            model.addAttribute("error",
                    "Unable to retrieve tournament statistics.");
        }

        return "reportResults";
    }
    @PostMapping("/report/save")
    public String saveReport(
            @RequestParam("tournament") String tournament,
            Model model) {

        try {
            List<PlayerTournamentStats> standings =
                    reportDAO.getTournamentStandings(tournament);

            StringBuilder reportText = new StringBuilder();

            reportText.append("TOURNAMENT STANDINGS REPORT\n");
            reportText.append("Tournament: ").append(tournament).append("\n");
            reportText.append("Generated: ").append(LocalDateTime.now()).append("\n\n");

            int rank = 1;

            for (PlayerTournamentStats player : standings) {

                reportText.append("Rank: ").append(rank++).append("\n");
                reportText.append("Player: ").append(player.getPlayerName()).append("\n");
                reportText.append("Matches Played: ").append(player.getMatchesPlayed()).append("\n");
                reportText.append("Sets Won: ").append(player.getSetsWon()).append("\n");
                reportText.append("Sets Lost: ").append(player.getSetsLost()).append("\n");
                reportText.append("Set Win Percentage: ").append(player.getSetWinPercentage()).append("%\n");
                reportText.append("Aces: ").append(player.getAces()).append("\n");
                reportText.append("Double Faults: ").append(player.getDoubleFaults()).append("\n");
                reportText.append("Average First Serve: ")
                        .append(player.getFirstServePercentage())
                        .append("%\n");
                reportText.append("Break Points Won: ").append(player.getBreakPointsWon()).append("\n");
                reportText.append("Total Points Won: ").append(player.getTotalPointsWon()).append("\n\n");
            }

            Path directory = Path.of("C:/cis2232");
            Files.createDirectories(directory);

            String timestamp = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            Path file = directory.resolve(
                    "Tournament_Report_" + timestamp + ".txt");

            Files.writeString(file, reportText.toString());

            model.addAttribute("message",
                    "Report successfully saved to " + file);

            model.addAttribute("tournament", tournament);
            model.addAttribute("standings", standings);

        } catch (SQLException | IOException e) {

            model.addAttribute("error",
                    "Unable to save the report: " + e.getMessage());
        }

        return "reportResults";
    }
}