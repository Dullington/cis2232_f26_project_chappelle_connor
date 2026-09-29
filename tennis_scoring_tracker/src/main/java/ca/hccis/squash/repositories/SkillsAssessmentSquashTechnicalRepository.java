package ca.hccis.squash.repositories;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsAssessmentSquashTechnicalRepository extends CrudRepository<SkillsAssessmentSquashTechnical, Integer> {
    /**
     * Use Spring Data JPA functionality to find a list  containing the
     * string passed in as a paramter.
     *
     * @param name The name to find
     * @return The list of items
     * @since 20251030
     * @author BJM
     */
    //https://www.baeldung.com/spring-jpa-like-queries
    List<SkillsAssessmentSquashTechnical> findByAthleteNameContaining(String name);
    List<SkillsAssessmentSquashTechnical> findByAssessorNameContaining(String name);

}