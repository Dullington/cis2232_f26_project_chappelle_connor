package ca.hccis.squash.bo;

import ca.hccis.squash.dao.SkillsAssessmentSquashTechnicalDAO;
import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import ca.hccis.squash.util.CisUtilityFile;

import java.util.ArrayList;

public class SkillsAssessmentSquashTechnicalBO {

    public static final int POINTS_FOREHAND_DRIVES = 15;
    public static final int POINTS_BACKHAND_DRIVES = 15;
    public static final int POINTS_VOLLEY_MAX = 8;
    public static final int POINTS_VOLLEY_SUM = 5;

    /**
     * Calculate the score based on attribute skill values
     *
     * @return the technical score
     * @author BJM
     * @since 20250915
     */
    public static int calculateTechnicalScore(SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical) {
        int score = 0;

        score = skillsAssessmentSquashTechnical.getForehandDrives() * POINTS_FOREHAND_DRIVES;
        score += skillsAssessmentSquashTechnical.getBackhandDrives() * POINTS_BACKHAND_DRIVES;
        score += skillsAssessmentSquashTechnical.getForehandVolleyMax() * POINTS_VOLLEY_MAX;
        score += skillsAssessmentSquashTechnical.getForehandVolleySum() * POINTS_VOLLEY_SUM;
        score += skillsAssessmentSquashTechnical.getBackhandVolleyMax() * POINTS_VOLLEY_MAX;
        score += skillsAssessmentSquashTechnical.getBackhandVolleySum() * POINTS_VOLLEY_SUM;

        skillsAssessmentSquashTechnical.setTechnicalScore(score);
        return score;
    }

    public ArrayList<SkillsAssessmentSquashTechnical> processSelectAllByAthleteAssessorName(String name) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        SkillsAssessmentSquashTechnicalDAO skillsAssessmentSquashTechnicalDAO = new SkillsAssessmentSquashTechnicalDAO();
        ArrayList<SkillsAssessmentSquashTechnical> assessments = skillsAssessmentSquashTechnicalDAO.selectAllByAthleteAssessorName(name);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("athleteAssessorNameReport", assessments);

        return assessments;
    }

}
