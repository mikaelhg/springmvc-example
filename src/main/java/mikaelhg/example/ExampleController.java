package mikaelhg.example;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    private final static String ENCODING = "application/json;charset=UTF-8";

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

    /**
     * Alas, Ember.js doesn't support JSON arrays directly, so we'll have to wrap
     * the results in a map before writing a better REST adapter.
     * http://stackoverflow.com/questions/16887507/ember-js-rest-adapter-handling-different-json-structure
     * https://github.com/toranb/ember-data-django-rest-adapter/blob/master/packages/ember-data-django-rest-adapter/lib/adapter.js
     */
    @RequestMapping(value="/examples", method=GET, produces=ENCODING)
    public @ResponseBody Map<String, List<Example>> listExamples() {
        return ImmutableMap.of("examples", dao.findAll());
    }

    @RequestMapping(value="/examples", method=PUT, produces=ENCODING)
    public @ResponseBody Example createExample(final @RequestBody Example example) {
        return dao.saveAndFlush(example);
    }

    @RequestMapping(value="/examples/{id}", method=GET, produces=ENCODING)
    public @ResponseBody Example readExample(final @PathVariable Long id) {
        return dao.findOne(id);
    }

    @RequestMapping(value="/examples/{id}", method=PUT, produces=ENCODING)
    public @ResponseBody Example updateExample(final @PathVariable Long id, final @RequestBody Example example) {
        final Example old = dao.findOne(id);
        example.setId(old.getId());
        return dao.saveAndFlush(example);
    }

    @RequestMapping(value="/examples/{id}", method=DELETE, produces=ENCODING)
    public @ResponseBody void deleteExample(final @PathVariable Long id) {
        final Example old = dao.findOne(id);
        dao.delete(old);
    }

}
