package mikaelhg.example

import javax.persistence._
import scala.reflect._
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.repository.annotation.RestResource

@Entity(name="examples")
class Example extends Id {

  @BeanProperty
  var name : java.lang.String = _

  def this(newName : java.lang.String) = {
    this()
    name = newName
  }

}

@RestResource(path = "examples")
abstract trait ExampleDao extends JpaRepository[Example, java.lang.Long] {
  def findByNameLike(nameLike: String): java.util.List[Example]

  @Query("SELECT e FROM mikaelhg.example.Example e WHERE e.name LIKE '%y%'")
  def findComedies: java.util.List[Example]
}

trait Id {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _
}
