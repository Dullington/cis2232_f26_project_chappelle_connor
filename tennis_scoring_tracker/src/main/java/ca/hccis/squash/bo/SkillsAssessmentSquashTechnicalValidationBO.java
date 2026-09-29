package ca.hccis.squash.bo;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import java.time.LocalDate;
import java.util.ArrayList;

public class SkillsAssessmentSquashTechnicalValidationBO {

    public static ArrayList<String> validateAssessmentDate(SkillsAssessmentSquashTechnical skillsAssessmentSquashTechnical) {

        ArrayList<String> errors = new ArrayList<>();

        String assessmentDate = skillsAssessmentSquashTechnical.getAssessmentDate();
        if (assessmentDate.length() != 10) {
            errors.add("Assessment date must be 10 length");
        }

        if (errors.isEmpty()) {
            try {
                LocalDate localDateAssessmentDate = LocalDate.parse(assessmentDate);
                LocalDate currentDate = LocalDate.now();
                int compareValue = localDateAssessmentDate.compareTo(currentDate);

                if (compareValue > 0) { //current date is less than assessment date
                    errors.add("Assessment date can not be in the futre");
                }
            } catch (Exception e) {
                errors.add("Could not parse start date");
            }
        }

        return errors;
    }

}
