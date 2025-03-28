package net.bvk.personalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class PersonalAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalAppApplication.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(PersonalAppApplication.class, args);
//        ConfigurableEnvironment environment = context.getEnvironment();
//        System.out.println(environment.getActiveProfiles()[0]);
    }
}
