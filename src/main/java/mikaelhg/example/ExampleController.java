package mikaelhg.example;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * This example application has only a single Spring MVC web controller,
 * which serves HTTP requests.
 */
@Controller
@ComponentScan
@EnableAutoConfiguration
@lombok.extern.slf4j.Slf4j
public class ExampleController {

    @Resource private ExampleService svc;

    @Resource private ExampleDao dao;

    public static void main(final String ... args) {
        SpringApplication.run(ExampleController.class, args);
    }

    @RequestMapping(value="/")
    public String welcome(final ModelMap model, final HttpServletResponse response) {
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        model.put("examples", dao.findAll(new Sort("id")));
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
