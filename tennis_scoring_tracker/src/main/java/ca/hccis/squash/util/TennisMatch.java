package ca.hccis.squash.util;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "tennis_match")
public class TennisMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Player information
    @NotBlank(message = "Player 1's first name is required")
    @Size(max = 100, message = "First name cannot exceed 100 characters")
    private String player1FirstName;

    @NotBlank(message = "Player 1's last name is required")
    @Size(max = 100)
    private String player1LastName;

    @Size(max = 10)
    private String player1DateOfBirth;

    @NotBlank(message = "Player 2's first name is required")
    @Size(max = 100)
    private String player2FirstName;

    @NotBlank(message = "Player 2's last name is required")
    @Size(max = 100)
    private String player2LastName;

    @Size(max = 10)
    private String player2DateOfBirth;

    // Match information
    @NotBlank(message = "Match date is required")
    @Size(max = 10)
    private String matchDate;

    private String createdDateTime;

    @Size(max = 201)
    private String winnerName;

    @NotBlank(message = "Tournament name is required")
    @Size(max = 150)
    private String tournamentName;

    @NotBlank(message = "Court surface is required")
    @Size(max = 20)
    private String courtSurface;

    @NotNull(message = "Sets played is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer setsPlayed;

    @NotNull(message = "Player 1 sets won is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player1SetsWon;

    @NotNull(message = "Player 2 sets won is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player2SetsWon;

    // Player 1 statistics
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player1Aces;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player1DoubleFaults;
    @NotNull(message = "First serve percentage is required")
    @PositiveOrZero(message = "Percentage cannot be negative")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private Integer player1FirstServe;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player1BreakPointsWon;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player1TotalPointsWon;

    // Player 2 statistics
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player2Aces;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player2DoubleFaults;
    @NotNull(message = "First serve percentage is required")
    @PositiveOrZero(message = "Percentage cannot be negative")
    @Max(value = 100, message = "Percentage cannot exceed 100")
    private Integer player2FirstServe;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player2BreakPointsWon;
    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Value cannot be negative")
    private Integer player2TotalPointsWon;

    public TennisMatch() {
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getPlayer1FirstName() { return player1FirstName; }
    public void setPlayer1FirstName(String value) { this.player1FirstName = value; }

    public String getPlayer1LastName() { return player1LastName; }
    public void setPlayer1LastName(String value) { this.player1LastName = value; }

    public String getPlayer1DateOfBirth() { return player1DateOfBirth; }
    public void setPlayer1DateOfBirth(String value) { this.player1DateOfBirth = value; }

    public String getPlayer2FirstName() { return player2FirstName; }
    public void setPlayer2FirstName(String value) { this.player2FirstName = value; }

    public String getPlayer2LastName() { return player2LastName; }
    public void setPlayer2LastName(String value) { this.player2LastName = value; }

    public String getPlayer2DateOfBirth() { return player2DateOfBirth; }
    public void setPlayer2DateOfBirth(String value) { this.player2DateOfBirth = value; }

    public String getMatchDate() { return matchDate; }
    public void setMatchDate(String value) { this.matchDate = value; }

    public String getCreatedDateTime() { return createdDateTime; }
    public void setCreatedDateTime(String value) { this.createdDateTime = value; }

    public String getWinnerName() { return winnerName; }
    public void setWinnerName(String value) { this.winnerName = value; }

    public String getTournamentName() { return tournamentName; }
    public void setTournamentName(String value) { this.tournamentName = value; }

    public String getCourtSurface() { return courtSurface; }
    public void setCourtSurface(String value) { this.courtSurface = value; }

    public Integer getSetsPlayed() { return setsPlayed; }
    public void setSetsPlayed(Integer value) { this.setsPlayed = value; }

    public Integer getPlayer1SetsWon() { return player1SetsWon; }
    public void setPlayer1SetsWon(Integer value) { this.player1SetsWon = value; }

    public Integer getPlayer2SetsWon() { return player2SetsWon; }
    public void setPlayer2SetsWon(Integer value) { this.player2SetsWon = value; }

    public Integer getPlayer1Aces() { return player1Aces; }
    public void setPlayer1Aces(Integer value) { this.player1Aces = value; }

    public Integer getPlayer2Aces() { return player2Aces; }
    public void setPlayer2Aces(Integer value) { this.player2Aces = value; }

    public Integer getPlayer1DoubleFaults() { return player1DoubleFaults; }
    public void setPlayer1DoubleFaults(Integer value) { this.player1DoubleFaults = value; }

    public Integer getPlayer2DoubleFaults() { return player2DoubleFaults; }
    public void setPlayer2DoubleFaults(Integer value) { this.player2DoubleFaults = value; }

    public Integer getPlayer1FirstServe() { return player1FirstServe; }
    public void setPlayer1FirstServe(Integer value) { this.player1FirstServe = value; }

    public Integer getPlayer2FirstServe() { return player2FirstServe; }
    public void setPlayer2FirstServe(Integer value) { this.player2FirstServe = value; }

    public Integer getPlayer1BreakPointsWon() { return player1BreakPointsWon; }
    public void setPlayer1BreakPointsWon(Integer value) { this.player1BreakPointsWon = value; }

    public Integer getPlayer2BreakPointsWon() { return player2BreakPointsWon; }
    public void setPlayer2BreakPointsWon(Integer value) { this.player2BreakPointsWon = value; }

    public Integer getPlayer1TotalPointsWon() { return player1TotalPointsWon; }
    public void setPlayer1TotalPointsWon(Integer value) { this.player1TotalPointsWon = value; }

    public Integer getPlayer2TotalPointsWon() { return player2TotalPointsWon; }
    public void setPlayer2TotalPointsWon(Integer value) { this.player2TotalPointsWon = value; }
}