package mikaelhg.example

import javax.persistence._
import scala.reflect._

@Entity(name="examples")
class Example extends Id {

  @BeanProperty
  var name : java.lang.String = _

  def this(newName : java.lang.String) = {
    this()
    name = newName
  }

}

trait Id {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _
}