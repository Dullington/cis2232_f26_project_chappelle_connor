package ca.hccis.squash.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TennisMatchRepository extends JpaRepository<TennisMatch, Integer> {

}
