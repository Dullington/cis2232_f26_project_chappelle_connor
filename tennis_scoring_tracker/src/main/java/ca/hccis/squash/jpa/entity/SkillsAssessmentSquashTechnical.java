package ca.hccis.squash.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "SkillsAssessmentSquashTechnical")
public class SkillsAssessmentSquashTechnical {

    //This is added to this jpa entity class as an example of @Transient.  If you add attributes to this
    //class that are not to be persisted to the database, they should be @Transient.
    @Transient
    private String skillsCode;

    public SkillsAssessmentSquashTechnical() {
        this.forehandDrives = 0;
        this.backhandDrives = 0;
        this.forehandVolleyMax = 0;
        this.forehandVolleySum = 0;
        this.backhandVolleyMax = 0;
        this.backhandVolleySum = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min=1, max = 10)
    @NotNull
    @Column(name = "assessmentDate", nullable = false, length = 10)
    private String assessmentDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "createdDateTime", nullable = false, length = 20)
    private String createdDateTime;

    @Size(min=1, max = 50)//TODO replace this without the min.
    @NotNull
    @Column(name = "athleteName", nullable = false, length = 50)
    private String athleteName;

    @Size(min=1, max = 50)
    @NotNull
    @Column(name = "assessorName", nullable = false, length = 50)
    private String assessorName;

    @Column(name = "forehandDrives")
    private Integer forehandDrives;

    @Column(name = "backhandDrives")
    private Integer backhandDrives;

    @Column(name = "forehandVolleyMax")
    private Integer forehandVolleyMax;

    @Column(name = "forehandVolleySum")
    private Integer forehandVolleySum;

    @Column(name = "backhandVolleyMax")
    private Integer backhandVolleyMax;

    @Column(name = "backhandVolleySum")
    private Integer backhandVolleySum;

    @Column(name = "technicalScore")
    private Integer technicalScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAssessorName() {
        return assessorName;
    }

    public void setAssessorName(String assessorName) {
        this.assessorName = assessorName;
    }

    public Integer getForehandDrives() {
        return forehandDrives;
    }

    public void setForehandDrives(Integer forehandDrives) {
        this.forehandDrives = forehandDrives;
    }

    public Integer getBackhandDrives() {
        return backhandDrives;
    }

    public void setBackhandDrives(Integer backhandDrives) {
        this.backhandDrives = backhandDrives;
    }

    public Integer getForehandVolleyMax() {
        return forehandVolleyMax;
    }

    public void setForehandVolleyMax(Integer forehandVolleyMax) {
        this.forehandVolleyMax = forehandVolleyMax;
    }

    public Integer getForehandVolleySum() {
        return forehandVolleySum;
    }

    public void setForehandVolleySum(Integer forehandVolleySum) {
        this.forehandVolleySum = forehandVolleySum;
    }

    public Integer getBackhandVolleyMax() {
        return backhandVolleyMax;
    }

    public void setBackhandVolleyMax(Integer backhandVolleyMax) {
        this.backhandVolleyMax = backhandVolleyMax;
    }

    public Integer getBackhandVolleySum() {
        return backhandVolleySum;
    }

    public void setBackhandVolleySum(Integer backhandVolleySum) {
        this.backhandVolleySum = backhandVolleySum;
    }

    public Integer getTechnicalScore() {
        return technicalScore;
    }

    public void setTechnicalScore(Integer technicalScore) {
        this.technicalScore = technicalScore;
    }

    @Override
    public String toString() {
        return "SkillsAssessmentSquashTechnical\n" +
                "    assessmentDate     = '" + assessmentDate + "',\n" +
                "    createdDateTime    = '" + createdDateTime + "',\n" +
                "    athleteName        = '" + athleteName + "',\n" +
                "    assessorName       = '" + assessorName + "',\n" +
                "    forehandDrives     = " + forehandDrives + ",\n" +
                "    backhandDrives     = " + backhandDrives + ",\n" +
                "    forehandVolleyMax  = " + forehandVolleyMax + ",\n" +
                "    forehandVolleySum  = " + forehandVolleySum + ",\n" +
                "    backhandVolleyMax  = " + backhandVolleyMax + ",\n" +
                "    backhandVolleySum  = " + backhandVolleySum + ",\n" +
                "    technicalScore     = " + technicalScore + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SkillsAssessmentSquashTechnical)) return false;
        SkillsAssessmentSquashTechnical that = (SkillsAssessmentSquashTechnical) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}