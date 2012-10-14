package mikaelhg.example

import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, ResponseBody, RequestMapping}
import org.springframework.ui.ModelMap
import scala.Array
import annotation.target.field
import javax.annotation.Resource

/**
 * This example application has only a single Spring MVC web controller,
 * which serves HTTP requests.
 */
@org.springframework.stereotype.Controller
class ExampleController {

  @(Resource @field) var exampleDao : ExampleDao = _
  @(Resource @field) var exampleService: ExampleService = _

  @RequestMapping(Array("/"))
  def welcome(model: ModelMap) = {
    model.put("examples", exampleDao.findAll)
    model.put("comedies", exampleDao.findComedies)
    "index"
  }

  @RequestMapping(value = Array("/examples"), produces = Array("application/json"))
  @ResponseBody
  def listExamples = exampleDao.findAll

  @RequestMapping(value = Array("/submitExample"), method = Array(RequestMethod.POST))
  def submitExample(@RequestParam name: String) = {
    exampleService.submitNewExample(new Example(name))
    "redirect:/"
  }

}