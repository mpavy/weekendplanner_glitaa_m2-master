package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"repository", "service", "web"})
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class WeekendPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekendPlannerApplication.class, args);
    }
}
