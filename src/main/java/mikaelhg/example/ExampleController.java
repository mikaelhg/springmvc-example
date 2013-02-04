package mikaelhg.example;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This example application has only a single Spring MVC web controller,
 * which serves HTTP requests.
 */
@Controller
@lombok.extern.slf4j.Slf4j
public class ExampleController {

    @Resource private ExampleService svc;

    @Resource private ExampleDao dao;

    @RequestMapping("/")
    public String welcome(final ModelMap model) {
        model.put("examples", dao.findAll());
        model.put("comedies", dao.findComedies());
        return "index";
    }

    @RequestMapping(value="/examples", produces="application/json")
    public @ResponseBody List<Example> listExamples() {
        return dao.findAll();
    }

    @RequestMapping(value="/submitExample", method=RequestMethod.POST)
    public String submitExample(@RequestParam final String name) {
        svc.submitNewExample(new Example(name));
        return "redirect:/";
    }

    @RequestMapping(value="/updateExample", method=RequestMethod.POST)
    public String updateExample(@RequestParam final Long pk, @RequestParam final String value) {
        svc.updateExample(pk, value);
        return "redirect:/";
    }

}
