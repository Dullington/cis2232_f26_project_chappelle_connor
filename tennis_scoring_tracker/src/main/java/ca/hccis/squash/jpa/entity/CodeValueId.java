package ca.hccis.squash.jpa.entity;

import org.hibernate.Hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CodeValueId implements Serializable {
    private static final long serialVersionUID = -6193982470728355174L;
    @NotNull
    @Column(name = "codeTypeId", nullable = false)
    private Integer codeTypeId;

    @NotNull
    @Column(name = "codeValueSequence", nullable = false)
    private Integer codeValueSequence;

    public Integer getCodeTypeId() {
        return codeTypeId;
    }

    public void setCodeTypeId(Integer codeTypeId) {
        this.codeTypeId = codeTypeId;
    }

    public Integer getCodeValueSequence() {
        return codeValueSequence;
    }

    public void setCodeValueSequence(Integer codeValueSequence) {
        this.codeValueSequence = codeValueSequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CodeValueId entity = (CodeValueId) o;
        return Objects.equals(this.codeTypeId, entity.codeTypeId) &&
                Objects.equals(this.codeValueSequence, entity.codeValueSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeTypeId, codeValueSequence);
    }

}