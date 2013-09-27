package mikaelhg.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@EnableHypermediaSupport
public class ExampleRestConfiguration extends RepositoryRestMvcConfiguration {


}
