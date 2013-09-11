package mikaelhg.example;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.DefaultRequestToViewNameTranslator;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * The application Spring Framework configuration class.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ExampleConfiguration {

    public static void main(final String ... args) {
        SpringApplication.run(ExampleConfiguration.class, args);
    }

    @Configuration
    @Profile("default")
    public static class DevelopmentProfileConfiguration {

        @Bean public DataSource dataSource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .addScript("classpath:mikaelhg/example/dev-schema.sql")
                    .addScript("classpath:mikaelhg/example/dev-test-data.sql")
                    .build();
        }

        @Bean public JpaVendorAdapter jpaVendorAdapter() {
            final HibernateJpaVendorAdapter ret =
                    new HibernateJpaVendorAdapter();
            ret.setDatabase(Database.H2);
            return ret;
        }
    }

    @Configuration
    @Profile("postgresql")
    public static class PostgreSQLProfileConfiguration {

        @Resource private Environment env;

        @Bean public DataSource dataSource() throws Exception {
            final ComboPooledDataSource ret = new ComboPooledDataSource();
            ret.setDriverClass(env.getProperty("db.driverClass", "org.postgresql.Driver"));
            ret.setUser(env.getProperty("db.username"));
            ret.setPassword(env.getProperty("db.password", ""));
            ret.setJdbcUrl(env.getProperty("db.url"));
            ret.setCheckoutTimeout(1000);
            ret.setUnreturnedConnectionTimeout(1000);
            return ret;
        }

        @Bean public JpaVendorAdapter jpaVendorAdapter() {
            final HibernateJpaVendorAdapter ret =
                    new HibernateJpaVendorAdapter();
            ret.setDatabase(Database.POSTGRESQL);
            return ret;
        }
    }

    @Configuration
    @Profile("mysql")
    public static class MySQLProfileConfiguration {

        @Resource private Environment env;

        @Bean(destroyMethod="close")
        public DataSource dataSource() throws Exception {
            final ComboPooledDataSource ret = new ComboPooledDataSource();
            ret.setDriverClass(env.getProperty("db.driverClass", "com.mysql.jdbc.Driver"));
            ret.setUser(env.getProperty("db.username"));
            ret.setPassword(env.getProperty("db.password", ""));
            ret.setJdbcUrl(env.getProperty("db.url"));
            ret.setCheckoutTimeout(1000);
            ret.setUnreturnedConnectionTimeout(1000);
            return ret;
        }

        @Bean public JpaVendorAdapter jpaVendorAdapter() {
            final HibernateJpaVendorAdapter ret =
                    new HibernateJpaVendorAdapter();
            ret.setDatabase(Database.MYSQL);
            return ret;
        }
    }

}
