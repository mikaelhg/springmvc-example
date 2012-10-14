package mikaelhg.example

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

abstract trait ExampleDao extends JpaRepository[Example, java.lang.Long] {
  def findByNameLike(nameLike: String): java.util.List[Example]

  @Query("SELECT e FROM mikaelhg.example.Example e WHERE e.name LIKE '%y%'")
  def findComedies: java.util.List[Example]
}
