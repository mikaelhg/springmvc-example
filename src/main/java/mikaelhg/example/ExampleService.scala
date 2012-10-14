package mikaelhg.example

import org.springframework.transaction.annotation.Transactional
import org.joda.time.DateTime._
import org.joda.time.DateTimeConstants.MONDAY
import javax.annotation.Resource
import annotation.target.field
import reflect.BeanProperty

@org.springframework.stereotype.Service
class ExampleService {

  @(Resource @field) var exampleDao : ExampleDao = _

  private val badWords = List("republican", "religion", "true finns")

  @Transactional
  def submitNewExample(example: Example): Boolean = {
    if (badWords.contains(example.name)) {
      return false
    } else {
      if (now.getDayOfWeek == MONDAY) {
        Console.err.println("Do I _have_ to... It's too early for this stuff!")
      }
      return null != exampleDao.save(example)
    }
  }

}
