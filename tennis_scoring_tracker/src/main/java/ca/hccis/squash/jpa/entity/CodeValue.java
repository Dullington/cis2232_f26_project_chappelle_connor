package ca.hccis.squash.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "CodeValue")
public class CodeValue {
    @EmbeddedId
    private CodeValueId id;

    @Size(max = 100)
    @NotNull
    @Column(name = "englishDescription", nullable = false, length = 100)
    private String englishDescription;

    @Size(max = 20)
    @NotNull
    @Column(name = "englishDescriptionShort", nullable = false, length = 20)
    private String englishDescriptionShort;

    @Size(max = 100)
    @Column(name = "frenchDescription", length = 100)
    private String frenchDescription;

    @Size(max = 20)
    @Column(name = "frenchDescriptionShort", length = 20)
    private String frenchDescriptionShort;

    @Column(name = "sortOrder")
    private Integer sortOrder;

    @Column(name = "createdDateTime")
    private Instant createdDateTime;

    @Size(max = 20)
    @Column(name = "createdUserId", length = 20)
    private String createdUserId;

    @Column(name = "updatedDateTime")
    private Instant updatedDateTime;

    @Size(max = 20)
    @Column(name = "updatedUserId", length = 20)
    private String updatedUserId;

    public CodeValueId getId() {
        return id;
    }

    public void setId(CodeValueId id) {
        this.id = id;
    }

    public String getEnglishDescription() {
        return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
        this.englishDescription = englishDescription;
    }

    public String getEnglishDescriptionShort() {
        return englishDescriptionShort;
    }

    public void setEnglishDescriptionShort(String englishDescriptionShort) {
        this.englishDescriptionShort = englishDescriptionShort;
    }

    public String getFrenchDescription() {
        return frenchDescription;
    }

    public void setFrenchDescription(String frenchDescription) {
        this.frenchDescription = frenchDescription;
    }

    public String getFrenchDescriptionShort() {
        return frenchDescriptionShort;
    }

    public void setFrenchDescriptionShort(String frenchDescriptionShort) {
        this.frenchDescriptionShort = frenchDescriptionShort;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Instant getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Instant createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }

    public Instant getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(Instant updatedDateTime) {
        this.updatedDateTime = updatedDateTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

}