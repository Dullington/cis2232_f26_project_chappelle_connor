package ca.hccis.squash.repositories;

import java.util.List;

import ca.hccis.squash.jpa.entity.CodeValue;
import ca.hccis.squash.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}