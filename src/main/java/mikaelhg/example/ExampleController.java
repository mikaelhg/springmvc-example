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

}
