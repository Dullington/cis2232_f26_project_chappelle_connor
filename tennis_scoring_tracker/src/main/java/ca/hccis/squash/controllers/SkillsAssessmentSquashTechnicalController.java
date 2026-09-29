package ca.hccis.squash.controllers;

import ca.hccis.squash.bo.SkillsAssessmentSquashTechnicalBO;
import ca.hccis.squash.bo.SkillsAssessmentSquashTechnicalValidationBO;
import ca.hccis.squash.entity.SkillsAssessmentSquashTechnicalDto;
import ca.hccis.squash.jpa.entity.CodeValue;
import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import ca.hccis.squash.repositories.CodeValueRepository;
import ca.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Controller to administer crud. Note that the code was taken from
 * Fred Campos' project from 2021 which also had modifications from Ferhad in
 * 2022.
 *
 * @author BJM
 * @since 20251023
 */
@Controller
@RequestMapping("/skillsassessmentsquashtechnical")
public class SkillsAssessmentSquashTechnicalController {

    private final SkillsAssessmentSquashTechnicalRepository _sastr;
    private final CodeValueRepository _cvr;

    @Autowired
    public SkillsAssessmentSquashTechnicalController(SkillsAssessmentSquashTechnicalRepository sastr, CodeValueRepository cvr) {
        _sastr = sastr;
        _cvr = cvr;
    }

    @Autowired
    private MessageSource messageSource;
    private static final Logger logger = LoggerFactory.getLogger(SkillsAssessmentSquashTechnicalController.class);

    @RequestMapping("")
    public String home(Model model, HttpSession session) {

        //Example for dynamic list using CodeValue
        //Work with the CodeValueRepository
        Iterable<CodeValue> codeValues = _cvr.findAll();
        ArrayList<CodeValue> skillTypes = new ArrayList<>();
        for (CodeValue codeValue : codeValues) {
            final int CODE_TYPE_ID_SKILL_TYPES = 2;
            if (codeValue.getId().getCodeTypeId() == CODE_TYPE_ID_SKILL_TYPES) {
                skillTypes.add(codeValue);
            }
        }
        session.setAttribute("skillTypes", skillTypes);


        Iterable<SkillsAssessmentSquashTechnical> assessments = _sastr.findAll();
        model.addAttribute("assessments", assessments);
        model.addAttribute("assessment", new SkillsAssessmentSquashTechnical());
        return "skillsassessmentsquashtechnical/list";
    }

    /**
     * Page to delete an entity
     *
     * @param id ID
     * @return redirect to the list page
     * @author BJM
     * @since 20251201
     */
    @RequestMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        try {
            _sastr.deleteById(id);
            model.addAttribute("messageSuccess", "Assessment deleted");
        } catch (Exception e) {
            //todo what if delete was not successful?
            model.addAttribute("messageError", "Exception deleting assessment");
        }
        Iterable<SkillsAssessmentSquashTechnical> assessments = _sastr.findAll();
        model.addAttribute("assessments", assessments);
        model.addAttribute("assessment", new SkillsAssessmentSquashTechnical());
        return "skillsassessmentsquashtechnical/list";
    }

    /**
     * Page to add new entity. Taken from tutor app from 2022 (which was
     * also derived from class samples)
     *
     * @param model
     * @return add
     * @author BJM
     * @since 2025-12-01
     */
    @RequestMapping("/add")
    public String add(Model model, HttpSession session) {

        SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical = new SkillsAssessmentSquashTechnical();
        model.addAttribute("skillsAssessment", skillsAssessmentSquashTechnical);
        return "skillsassessmentsquashtechnical/add";
    }

    /**
     * Page to edit
     *
     * @param id    ID
     * @param model
     * @author BJM
     * @since 20251201
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model, HttpSession session) {

        Optional skillsAssessmentSquashTechnical = _sastr.findById(id);
        if (skillsAssessmentSquashTechnical.isPresent()) {
            model.addAttribute("skillsAssessment", skillsAssessmentSquashTechnical.get());
            return "skillsassessmentsquashtechnical/add";
        }

        //todo How can we best communcicate this to the view.
        model.addAttribute("messageError", "Could not load the assessment");
        Iterable<SkillsAssessmentSquashTechnical> assessments = _sastr.findAll();
        model.addAttribute("assessments", assessments);
        model.addAttribute("assessment", new SkillsAssessmentSquashTechnical());
        return "skillsassessmentsquashtechnical/list";
    }

    /**
     * Submit method that processes add and edit and any form submission
     *
     * @param model
     * @param request
     * @param skillsAssessmentSquashTechnical what is being added or modified
     * @param bindingResult                   Result of SQL
     * @return add with errors or busPass
     * @author CIS2232
     * @since 20251201
     */
    @RequestMapping("/submit")
    public String submit(Model model, HttpServletRequest request, @Valid @ModelAttribute("skillsAssessment") SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical, BindingResult bindingResult) {
        boolean valid = true;

        //Sprint 5 business validation
        ArrayList<String> validationErrors = SkillsAssessmentSquashTechnicalValidationBO.validateAssessmentDate(skillsAssessmentSquashTechnical);
        if (validationErrors.size() > 0) {
            valid = false;
        }

        if (!valid || bindingResult.hasErrors()) {
            System.out.println("--------------------------------------------");
            System.out.println("Validation error - BJM");
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.out.println(error.getObjectName() + "-" + error.toString() + "-" + error.getDefaultMessage());
            }
            System.out.println("--------------------------------------------");
            skillsAssessmentSquashTechnical.setTechnicalScore(0);
            model.addAttribute("skillsAssessment", skillsAssessmentSquashTechnical);
            model.addAttribute("businessValidationErrorsAssessmentDate", validationErrors);
            return "skillsassessmentsquashtechnical/add";
        }

        //BJM 20251124 calculate the score before saving.
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(skillsAssessmentSquashTechnical);
        _sastr.save(skillsAssessmentSquashTechnical);
        return "redirect:/skillsassessmentsquashtechnical";
    }

    /**
     * Search for a athlete name
     *
     * @param model
     * @param skillsAssessmentSquashTechnical
     * @return view for list
     * @author BJM
     * @since 2025-12-01
     */
    @RequestMapping("/search")
    public String search(Model model, @ModelAttribute("assessment") SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical) {

        //**********************************************************************
        //Use repository method created to find any entities which contain
        //the name entered on the list page.
        //**********************************************************************

        List<SkillsAssessmentSquashTechnical> assessmentsAthleteName = _sastr.findByAthleteNameContaining(skillsAssessmentSquashTechnical.getAthleteName());
        List<SkillsAssessmentSquashTechnical> assessmentsAssessorName = _sastr.findByAssessorNameContaining(skillsAssessmentSquashTechnical.getAthleteName());

        //put in set to eliminate duplicates
        HashSet<SkillsAssessmentSquashTechnical> skillsAssessmentSquashTechnicalsSet = new HashSet<>();
        skillsAssessmentSquashTechnicalsSet.addAll(assessmentsAthleteName);
        skillsAssessmentSquashTechnicalsSet.addAll(assessmentsAssessorName);

        model.addAttribute("assessments", skillsAssessmentSquashTechnicalsSet);
        logger.debug("searched for name:" + skillsAssessmentSquashTechnical.getAthleteName());
        return "skillsassessmentsquashtechnical/list";
    }

    @RequestMapping("/list/edit")
    public String showCreateForm(Model model) {
        SkillsAssessmentSquashTechnicalDto skillsAssessmentSquashTechnicalForm = new SkillsAssessmentSquashTechnicalDto();


        Iterable<SkillsAssessmentSquashTechnical> assessments = _sastr.findAll();
        ArrayList<SkillsAssessmentSquashTechnical> tempList = new ArrayList<>();
        for (SkillsAssessmentSquashTechnical current : assessments) {
            tempList.add(current);
        }
        skillsAssessmentSquashTechnicalForm.setAssessments(tempList);
        model.addAttribute("form", skillsAssessmentSquashTechnicalForm);
        return "skillsassessmentsquashtechnical/listedit";
    }

    @RequestMapping("/list/edit/submit")
    public String listEditSubmit(@ModelAttribute SkillsAssessmentSquashTechnicalDto form, Model model) {

        //TODO calculate all scores before saving

        _sastr.saveAll(form.getAssessments());

        SkillsAssessmentSquashTechnicalDto skillsAssessmentSquashTechnicalForm = new SkillsAssessmentSquashTechnicalDto();

        Iterable<SkillsAssessmentSquashTechnical> assessments = _sastr.findAll();
        ArrayList<SkillsAssessmentSquashTechnical> tempList = new ArrayList<>();
        for (SkillsAssessmentSquashTechnical current : assessments) {
            tempList.add(current);
        }
        skillsAssessmentSquashTechnicalForm.setAssessments(tempList);
        model.addAttribute("form", skillsAssessmentSquashTechnicalForm);
        return "skillsassessmentsquashtechnical/listedit";

    }
}
