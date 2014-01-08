package mikaelhg.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This example application has only a single Spring MVC web controller,
 * which serves HTTP requests.
 */
@Controller
@ComponentScan
@EnableAutoConfiguration
@lombok.extern.slf4j.Slf4j
public class ExampleController {

    public static void main(final String... args) {
        new SpringApplicationBuilder(ExampleController.class).run(args);
    }

    /**
     * We want every request/response pair to be handled as UTF-8, and every response identify itself as UTF-8.
     */
    @Bean
    public javax.servlet.Filter characterSetFilter() {
        return new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(final HttpServletRequest req, final HttpServletResponse res, final FilterChain chain)
                    throws ServletException, IOException
            {
                req.setCharacterEncoding("UTF-8");
                res.setCharacterEncoding("UTF-8");
                chain.doFilter(req, res);
            }
        };
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
