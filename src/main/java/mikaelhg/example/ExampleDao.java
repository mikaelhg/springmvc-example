package mikaelhg.example;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * A simple Spring Data JPA data access object interface for our examples.
 * Spring Data JPA automatically generates and provides an implementation of
 * this interface, when Spring boots up our application.
 */
@RestResource(rel = "examples", path = "examples")
public interface ExampleDao extends JpaRepository<Example, Long> {
    List<Example> findByNameLike(final String nameLike);

    @Query("SELECT e FROM mikaelhg.example.Example e WHERE e.name LIKE '%y%'")
    List<Example> findComedies();
}
