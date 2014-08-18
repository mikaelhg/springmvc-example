package mikaelhg.example;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Builder;

/**
 * Example domain class.
 */
@Entity(name="examples")
@Data @NoArgsConstructor @RequiredArgsConstructor @AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Example implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /** A name to show in a user interface. */
    @lombok.NonNull
    private String name;

}
