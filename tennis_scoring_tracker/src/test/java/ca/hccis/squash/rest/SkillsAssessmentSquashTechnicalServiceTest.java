package ca.hccis.squash.rest;

import ca.hccis.squash.jpa.entity.SkillsAssessmentSquashTechnical;
import ca.hccis.squash.repositories.SkillsAssessmentSquashTechnicalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SkillsAssessmentSquashTechnicalServiceTest {

    @Mock
    private SkillsAssessmentSquashTechnicalRepository repo;

    @InjectMocks
    private SkillsAssessmentSquashTechnicalService service;

    @Test
    void create_calculatesTechnicalScoreAndReturnsSavedAssessment() {
        SkillsAssessmentSquashTechnical input = new SkillsAssessmentSquashTechnical();
        input.setAssessmentDate("2025-06-17");
        input.setCreatedDateTime("20250617120000");
        input.setAthleteName("Jane Doe");
        input.setAssessorName("Coach Smith");
        input.setForehandDrives(1);
        input.setBackhandDrives(1);
        input.setForehandVolleyMax(1);
        input.setForehandVolleySum(1);
        input.setBackhandVolleyMax(1);
        input.setBackhandVolleySum(1);

        when(repo.save(any(SkillsAssessmentSquashTechnical.class))).thenAnswer(invocation -> {
            SkillsAssessmentSquashTechnical saved = invocation.getArgument(0);
            saved.setId(1);
            return saved;
        });

        ResponseEntity<SkillsAssessmentSquashTechnical> response = service.create(input);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals(56, response.getBody().getTechnicalScore());
        assertEquals("Jane Doe", response.getBody().getAthleteName());

        ArgumentCaptor<SkillsAssessmentSquashTechnical> captor =
                ArgumentCaptor.forClass(SkillsAssessmentSquashTechnical.class);
        verify(repo).save(captor.capture());
        assertEquals(56, captor.getValue().getTechnicalScore());
    }
}
