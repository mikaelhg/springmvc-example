package mikaelhg.example

import org.springframework.web.WebApplicationInitializer
import javax.servlet.{ServletRegistration, ServletContext}
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.servlet.DispatcherServlet

class ExampleApplication extends WebApplicationInitializer {
  override def onStartup(container: ServletContext) {
    val rootContext: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext
    container.addListener(new ContextLoaderListener(rootContext))
    val dispatcherContext: AnnotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext
    dispatcherContext.register(classOf[mikaelhg.example.ExampleConfiguration])
    val dispatcher: ServletRegistration.Dynamic = container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext))
    dispatcher.setLoadOnStartup(1)
    dispatcher.addMapping("/*")
  }
}
