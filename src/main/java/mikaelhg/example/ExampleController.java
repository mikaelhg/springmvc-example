package mikaelhg.example;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
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
        new SpringApplicationBuilder(ExampleController.class).web(true).run(args);
    }

    @FunctionalInterface
    private static interface LambdaFilter extends Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                             FilterChain chain) throws IOException, ServletException;

        @Override
        default public void init(FilterConfig filterConfig) throws ServletException {}

        @Override
        default public void destroy() {}
    }

    /**
     * We want every request/response pair to be handled as UTF-8, and every response identify itself as UTF-8.
     */
    @Bean
    public Filter characterSetFilter() {
        return (LambdaFilter) (req, res, chain) -> {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");
            chain.doFilter(req, res);
        };
    }

    @RequestMapping("/")
    public String welcome() {
        return "index";
    }

}
