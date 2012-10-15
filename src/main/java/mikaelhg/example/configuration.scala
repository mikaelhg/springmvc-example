package mikaelhg.example

import org.springframework.context.annotation._
import org.springframework.web.servlet.config.annotation.{ResourceHandlerRegistry, WebMvcConfigurerAdapter, EnableWebMvc}
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.jdbc.datasource.embedded.{EmbeddedDatabaseType, EmbeddedDatabaseBuilder}
import org.springframework.orm.jpa.{JpaTransactionManager, LocalContainerEntityManagerFactoryBean, JpaVendorAdapter}
import org.springframework.orm.jpa.vendor.{Database, HibernateJpaVendorAdapter}
import javax.sql.DataSource
import org.postgresql.ds.PGPoolingDataSource
import javax.annotation.Resource
import org.springframework.core.env.Environment
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.springframework.orm.hibernate4.HibernateExceptionTranslator
import org.springframework.web.servlet.view.{JstlView, InternalResourceViewResolver, DefaultRequestToViewNameTranslator}
import org.fusesource.scalate.spring.view.ScalateViewResolver
import javax.persistence.EntityManagerFactory
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

@Configuration
@Profile(Array("default"))
class DevelopmentProfileConfiguration {
  @Bean
  def dataSource() = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
    .addScript("classpath:mikaelhg/example/dev-schema.sql")
    .addScript("classpath:mikaelhg/example/dev-test-data.sql").build

  @Bean def jpaVendorAdapter() : JpaVendorAdapter = {
    val ret = new HibernateJpaVendorAdapter
    ret.setDatabase(Database.H2)
    ret
  }
}

@Configuration
@Profile(Array("postgresql"))
class PostgreSQLProfileConfiguration {

  @Bean(destroyMethod = "close") def dataSource() : DataSource = {
    val ret = new PGPoolingDataSource
    ret.setServerName(env.getProperty("db.servername"))
    ret.setDatabaseName(env.getProperty("db.databasename"))
    ret.setUser(env.getProperty("db.user"))
    ret.setPassword(env.getProperty("db.password"))
    ret
  }

  @Bean def jpaVendorAdapter() : JpaVendorAdapter = {
    val ret = new HibernateJpaVendorAdapter
    ret.setDatabase(Database.POSTGRESQL)
    ret
  }

  @Resource private var env: Environment = _
}

@Configuration
@Profile(Array("mysql"))
class MySQLProfileConfiguration {

  @Bean(destroyMethod = "close") def dataSource() : DataSource = {
    val ret = new ComboPooledDataSource
    ret.setJdbcUrl(env.getProperty("db.url"))
    ret.setDriverClass(env.getProperty("db.driverClass", "com.mysql.jdbc.Driver"))
    ret.setUser(env.getProperty("db.username"))
    ret.setPassword(env.getProperty("db.password", ""))
    ret.setCheckoutTimeout(1000)
    ret.setUnreturnedConnectionTimeout(1000)
    ret
  }

  @Bean def jpaVendorAdapter() : JpaVendorAdapter = {
    val ret = new HibernateJpaVendorAdapter
    ret.setDatabase(Database.MYSQL)
    ret
  }

  @Resource private var env: Environment = _
}

@Configuration
@EnableJpaRepositories(basePackages = Array("mikaelhg.example"))
@ComponentScan(basePackages = Array("mikaelhg.example"))
@EnableWebMvc
@EnableTransactionManagement(proxyTargetClass = true) // Can't use Scala + Spring without using CGLIB proxies
class ExampleConfiguration extends WebMvcConfigurerAdapter {

  @Bean def jpaExceptionTranslator() = new HibernateExceptionTranslator

  @Bean def viewTranslator() = new DefaultRequestToViewNameTranslator

  @Bean
  def scalateViewResolver() = new ScalateViewResolver {
    setOrder(1)
    setPrefix("/WEB-INF/view/")
    setSuffix(".scaml")
  }

  @Bean
  def internalResourceViewResolver() = new InternalResourceViewResolver {
    setOrder(2)
    setViewClass(classOf[JstlView])
    setPrefix("/WEB-INF/jsp/")
    setSuffix(".jsp")
  }

  @Bean
  def entityManagerFactory(ds: DataSource, jva: JpaVendorAdapter) =
    new LocalContainerEntityManagerFactoryBean {
      setPackagesToScan("mikaelhg.example")
      setDataSource(ds)
      setJpaVendorAdapter(jva)
      afterPropertiesSet
    }

  @Bean
  def transactionManager(emf: EntityManagerFactory) = new JpaTransactionManager {
    setEntityManagerFactory(emf)
  }

  override def addResourceHandlers(reg: ResourceHandlerRegistry) {
    reg.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/")
  }
}