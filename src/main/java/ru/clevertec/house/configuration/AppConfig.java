package ru.clevertec.house.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;


/**
 * Конфигурационный класс для работы с базой данных
 */
@Configuration
@Import({YamlConfig.class})
@ComponentScan(basePackages = "ru.clevertec.house")
@PropertySource(value = "classpath:application.yml")
@EnableWebMvc
@EnableTransactionManagement
@Slf4j
public class AppConfig {

    /**
     * Зависимость для работы с данными из application.yml
     */
    @Autowired
    private BeanFactoryPostProcessor beanFactoryPostProcessor;
    /**
     * Имя пользователя для подключение к БД
     */
    @Value("${spring.datasource.username}")
    private String userName;
    /**
     * Пароль пользователя для подключение к БД
     */
    @Value("${spring.datasource.password}")
    private String password;
    /**
     * Имя сервера
     */
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
//    /**
//     * Поле отвечающее на вопрос: необходимо ли мигрировать данные в БД с помощью Liquibase
//     */
//    @Value("${liquibase.enable}")
//    private String liquibaseEnable;
//    /**
//     * Путь, по которому находятся changeLog'и для Liquibase
//     */
//    @Value("${liquibase.change-log}")
//    private String liquibaseChangeLog;

    /**
     * Бин для подключения к БД
     *
     * @return возвращается DataSource для дальнейшей работы с БД
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public HttpHeaders header() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.setAcceptCharset(List.of(StandardCharsets.UTF_8));
        return httpHeaders;
    }

//    @Bean
//    public ObjectMapper mapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule());
//        return mapper;
//    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.clevertec.house.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();

        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }

//
//    /**
//     * Бин для накатывания данных в БД
//     *
//     * @return настроенный объект для миграции данных
//     */
//    @SneakyThrows
//    @Bean
//    public SpringLiquibase migrationDB() {
//        SpringLiquibase liquibase = new SpringLiquibase();
//        liquibase.setShouldRun(Boolean.parseBoolean(liquibaseEnable));
//        liquibase.setChangeLog(liquibaseChangeLog);
//        liquibase.setDataSource(dataSource());
//        return liquibase;
//    }
}
