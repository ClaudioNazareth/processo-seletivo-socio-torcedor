package br.com.sociotorcedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author : Claudio Nazareth  chtnazareth@gmail.com
 */

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients
@EnableMongoRepositories(basePackages = "br.com.sociotorcedor.repository")
public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        ApplicationContext app = SpringApplication.run(ApplicationStarter.class, args);
    }

}
