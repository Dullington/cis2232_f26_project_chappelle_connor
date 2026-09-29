package ca.hccis.squash.bo;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SkillsAssessmentSquashTechnicalBOTest {

    @Test
    void calculateTechnicalScore_all1() {
        SkillsAssessmentSquashTechnical squashSkillsAssessment= new SkillsAssessmentSquashTechnical();
        squashSkillsAssessment.setForehandDrives(1);
        squashSkillsAssessment.setBackhandDrives(1);
        squashSkillsAssessment.setBackhandVolleyMax(1);
        squashSkillsAssessment.setForehandVolleyMax(1);
        squashSkillsAssessment.setBackhandVolleySum(1);
        squashSkillsAssessment.setForehandVolleySum(1);

        int actual = SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(squashSkillsAssessment);

        Assertions.assertEquals(56, actual);
        Assertions.assertEquals(56, squashSkillsAssessment.getTechnicalScore());
    }

    @Test
    void calculateTechnicalScore_FH_Drive1() {
        SkillsAssessmentSquashTechnical squashSkillsAssessment= new SkillsAssessmentSquashTechnical();
        squashSkillsAssessment.setForehandDrives(1);

        int actual = SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(squashSkillsAssessment);

        Assertions.assertEquals(15, actual);
        Assertions.assertEquals(15, squashSkillsAssessment.getTechnicalScore());
    }

    @Test
    void calculateTechnicalScore_zeroValuesReturnsZero() {
        SkillsAssessmentSquashTechnical squashSkillsAssessment = new SkillsAssessmentSquashTechnical();

        int actual = SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(squashSkillsAssessment);

        Assertions.assertEquals(0, actual);
        Assertions.assertEquals(0, squashSkillsAssessment.getTechnicalScore());
    }

    @Test
    void calculateTechnicalScore_mixedValues() {
        SkillsAssessmentSquashTechnical squashSkillsAssessment = new SkillsAssessmentSquashTechnical();
        squashSkillsAssessment.setForehandDrives(2);
        squashSkillsAssessment.setBackhandDrives(3);
        squashSkillsAssessment.setForehandVolleyMax(1);
        squashSkillsAssessment.setForehandVolleySum(4);
        squashSkillsAssessment.setBackhandVolleyMax(2);
        squashSkillsAssessment.setBackhandVolleySum(3);

        int expected = 2 * SkillsAssessmentSquashTechnicalBO.POINTS_FOREHAND_DRIVES
                + 3 * SkillsAssessmentSquashTechnicalBO.POINTS_BACKHAND_DRIVES
                + 1 * SkillsAssessmentSquashTechnicalBO.POINTS_VOLLEY_MAX
                + 4 * SkillsAssessmentSquashTechnicalBO.POINTS_VOLLEY_SUM
                + 2 * SkillsAssessmentSquashTechnicalBO.POINTS_VOLLEY_MAX
                + 3 * SkillsAssessmentSquashTechnicalBO.POINTS_VOLLEY_SUM;

        int actual = SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(squashSkillsAssessment);

        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected, squashSkillsAssessment.getTechnicalScore());
    }

}