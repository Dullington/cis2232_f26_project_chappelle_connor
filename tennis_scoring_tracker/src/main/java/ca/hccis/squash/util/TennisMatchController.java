package ca.hccis.squash.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/matches")
public class TennisMatchController {

    private final TennisMatchRepository tennisMatchRepository;

    public TennisMatchController(TennisMatchRepository tennisMatchRepository) {
        this.tennisMatchRepository = tennisMatchRepository;
    }

    @GetMapping
    public String listMatches(Model model) {
        model.addAttribute("matches", tennisMatchRepository.findAll());
        return "matches";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("tennisMatch", new TennisMatch());
        return "matchForm";
    }

    @PostMapping("/save")
    public String saveMatch(
            @Valid @ModelAttribute("tennisMatch") TennisMatch tennisMatch,
            BindingResult result) {

        if (result.hasErrors()) {
            return "matchForm";
        }

        // Generate creation timestamp
        if (tennisMatch.getId() == null) {
            tennisMatch.setCreatedDateTime(LocalDateTime.now().toString());
        } else {
            TennisMatch existingMatch = tennisMatchRepository
                    .findById(tennisMatch.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid match ID"));

            tennisMatch.setCreatedDateTime(existingMatch.getCreatedDateTime());
        }

        // Determine the winner
        if (tennisMatch.getPlayer1SetsWon() > tennisMatch.getPlayer2SetsWon()) {
            tennisMatch.setWinnerName(
                    tennisMatch.getPlayer1FirstName() + " " +
                            tennisMatch.getPlayer1LastName());
        } else if (tennisMatch.getPlayer2SetsWon() > tennisMatch.getPlayer1SetsWon()) {
            tennisMatch.setWinnerName(
                    tennisMatch.getPlayer2FirstName() + " " +
                            tennisMatch.getPlayer2LastName());
        } else {
            tennisMatch.setWinnerName("Incomplete");
        }

        tennisMatchRepository.save(tennisMatch);

        return "redirect:/matches";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {

        TennisMatch tennisMatch = tennisMatchRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid match ID: " + id));

        model.addAttribute("tennisMatch", tennisMatch);

        return "matchForm";
    }
    @PostMapping("/delete/{id}")
    public String deleteMatch(@PathVariable Integer id) {

        tennisMatchRepository.deleteById(id);

        return "redirect:/matches";
    }
}