package ru.clevertec.house.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;


/**
 * Конфигурационный класс для работы с базой данных
 */
@EnableWebMvc
@Configuration
@EnableTransactionManagement
@Import({YamlConfig.class, DBConfig.class})
@ComponentScan(basePackages = "ru.clevertec.house")
@PropertySource(value = "classpath:application.yml")
public class AppConfig {


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
//        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return mapper;
//    }


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
