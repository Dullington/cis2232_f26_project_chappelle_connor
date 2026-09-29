package ca.hccis.squash.controllers;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.springframework.ui.Model;
import ca.hccis.squash.repositories.CodeValueRepository;
import ca.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import ca.hccis.squash.util.CisUtility;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Base controller which control general functionality in the app.
 *
 * @since 20220624
 * @author BJM
 */
@Controller
public class BaseController {

    private final CodeValueRepository _cvr;
    private final SkillsAssessmentSquashTechnicalRepository _sastr;

    @Autowired
    public BaseController(SkillsAssessmentSquashTechnicalRepository sastr, CodeValueRepository cvr) {
        _sastr = sastr;
        _cvr = cvr;
    }

    /**
     * Send the user to the welcome view
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/")
    public String home(HttpSession session, Model model) {


        //BJM 20200602 Issue#1 Set the current date in the session
        String currentDate = CisUtility.getCurrentDate("yyyy-MM-dd");
        session.setAttribute("currentDate", currentDate);

        //BusPassBO.setBusPassTypes(_cvr, session);
        ArrayList<SkillsAssessmentSquashTechnical> assessments = new ArrayList();
        _sastr.findAll().forEach(assessments::add);
        assessments.sort((a, b) -> a.getTechnicalScore().compareTo(b.getTechnicalScore()));
        model.addAttribute("assessments", assessments);

        return "index";
    }

    /**
     * Send the user to the about view.
     *
     * @since 20220624
     * @author BJM
     */
    @RequestMapping("/about")
    public String about() {
        return "other/about";
    }
}
