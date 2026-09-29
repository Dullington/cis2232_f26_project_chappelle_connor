package ca.hccis.squash.rest;

import ca.hccis.squash.bo.SkillsAssessmentSquashTechnicalBO;
import ca.hccis.squash.exception.AllAttributesNeededException;
import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import ca.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for accessing using REST.
 *
 * @author BJM
 * @since 20251110
 */
@RestController
@RequestMapping("/api/SASTService/v1/assessments")
public class SkillsAssessmentSquashTechnicalService {

    private final SkillsAssessmentSquashTechnicalRepository repo;

    @Autowired
    public SkillsAssessmentSquashTechnicalService(SkillsAssessmentSquashTechnicalRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<SkillsAssessmentSquashTechnical>> getAll() {

        Iterable<SkillsAssessmentSquashTechnical> assessmentsIterable = repo.findAll();
        List<SkillsAssessmentSquashTechnical> assessments = new ArrayList<>();
        assessmentsIterable.forEach(assessments::add);

        if (assessments == null || assessments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(assessments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillsAssessmentSquashTechnical> getById(@PathVariable Integer id) {

        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        return repo.findById(id)
                .map(entity -> {
                    repo.delete(entity);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<SkillsAssessmentSquashTechnical> create(
            @RequestBody SkillsAssessmentSquashTechnical obj) {

        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(obj);
        SkillsAssessmentSquashTechnical saved = repo.save(obj);

        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillsAssessmentSquashTechnical> update(
            @PathVariable Integer id,
            @RequestBody SkillsAssessmentSquashTechnical obj) {

        obj.setId(id);
        SkillsAssessmentSquashTechnicalBO.calculateTechnicalScore(obj);

        return ResponseEntity.ok(repo.save(obj));
    }
}