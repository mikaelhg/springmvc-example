package mikaelhg.example;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The application initializer class.
 * 1. The servlet container searches for the missing web.xml file.
 * 2. The servlet container searches for Servlet 3.0 programmatic configuration
 * annotations, finds them from the Spring 3.1 web package, and starts Spring.
 * 3. Spring looks for classes implementing WebApplicationInitializer, and
 * calls their onStartup methods.
 * 4. The onStartup method defines the application configuration, in this case
 * a Spring 3.1 MVC application, configured programmatically, with annotations.
 */
public class ExampleApplication implements WebApplicationInitializer {
    @Override
    public void onStartup(final ServletContext container) throws ServletException {
        final AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        container.addListener(new ContextLoaderListener(rootContext));
        final AnnotationConfigWebApplicationContext dispatcherContext =
                new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(ExampleConfiguration.class);
        final ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/*");
    }
}
