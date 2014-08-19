package mikaelhg.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * A simple Spring Data JPA data access object interface for our examples.
 * Spring Data JPA automatically generates and provides an implementation of
 * this interface, when Spring boots up our application.
 */
@RepositoryRestResource(path = "examples")
public interface ExampleDao extends JpaRepository<Example, Long> {

    List<Example> findByNameLike(final @Param("name") String nameLike);

    @Query("SELECT e FROM mikaelhg.example.dao.Example e WHERE e.name LIKE '%y%'")
    List<Example> findComedies();

}
