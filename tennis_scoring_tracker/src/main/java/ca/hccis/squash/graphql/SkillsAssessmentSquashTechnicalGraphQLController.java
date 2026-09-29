package ca.hccis.squash.graphql;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import ca.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import ca.hccis.squash.bo.SkillsAssessmentSquashTechnicalBO;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SkillsAssessmentSquashTechnicalGraphQLController {

    private final SkillsAssessmentSquashTechnicalRepository repo;

    public SkillsAssessmentSquashTechnicalGraphQLController(SkillsAssessmentSquashTechnicalRepository repo) {
        this.repo = repo;
    }

    @QueryMapping
    public List<SkillsAssessmentSquashTechnical> assessments() {
        List<SkillsAssessmentSquashTechnical> all = new ArrayList<>();
        repo.findAll().forEach(all::add);
        return all;
    }

    @QueryMapping
    public SkillsAssessmentSquashTechnical assessmentById(@Argument Integer id) {
        Optional<SkillsAssessmentSquashTechnical> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @QueryMapping
    public List<SkillsAssessmentSquashTechnical> findByAthlete(@Argument String name) {
        return repo.findByAthleteNameContaining(name);
    }

    @QueryMapping
    public List<SkillsAssessmentSquashTechnical> findByAssessor(@Argument String name) {
        return repo.findByAssessorNameContaining(name);
    }

    @MutationMapping
    public SkillsAssessmentSquashTechnical createAssessment(@Argument SkillsAssessmentSquashTechnicalInput input) {
        SkillsAssessmentSquashTechnical entity = input.toEntity();
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public SkillsAssessmentSquashTechnical updateAssessment(@Argument Integer id, @Argument SkillsAssessmentSquashTechnicalInput input) {
        SkillsAssessmentSquashTechnical entity = input.toEntity();
        entity.setId(id);
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(entity);
        return repo.save(entity);
    }

    @MutationMapping
    public Boolean deleteAssessment(@Argument Integer id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}