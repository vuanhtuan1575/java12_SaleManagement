package java12.projectsalemanagement;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ProjectSaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSaleManagementApplication.class, args);
    }

}
