package ca.hccis.squash.entity;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 * @author bjmaclean
 * @since 20251006
 */
public class ReportSquash {
    private String name;
    private int minScore;
    private int maxScore;
    private ArrayList<SkillsAssessmentSquashTechnical> skillsAssessmentSquashTechnicals;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public ArrayList<SkillsAssessmentSquashTechnical> getSkillsAssessmentSquashTechnicals() {
        return skillsAssessmentSquashTechnicals;
    }

    public void setSkillsAssessmentSquashTechnicals(ArrayList<SkillsAssessmentSquashTechnical> skillsAssessmentSquashTechnicals) {
        this.skillsAssessmentSquashTechnicals = skillsAssessmentSquashTechnicals;
    }
}
