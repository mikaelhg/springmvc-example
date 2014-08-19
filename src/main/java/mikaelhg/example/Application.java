package mikaelhg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This example application has only a single Spring MVC web controller,
 * which serves HTTP requests.
 */
@Controller
@ComponentScan
@EnableAutoConfiguration
@lombok.extern.slf4j.Slf4j
public class Application {

    public static void main(final String... args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
