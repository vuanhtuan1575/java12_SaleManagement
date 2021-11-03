package java12.projectsalemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java12.projectsalemanagement.upload.FileStorageProperties;

//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({ FileStorageProperties.class })
public class ProjectSaleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectSaleManagementApplication.class, args);
	}

}
