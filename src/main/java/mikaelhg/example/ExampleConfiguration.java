package mikaelhg.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * The application Spring Framework configuration class.
 */
@ComponentScan
@EnableAutoConfiguration
public class ExampleConfiguration {

    public static void main(final String ... args) {
        SpringApplication.run(ExampleConfiguration.class, args);
    }

}
