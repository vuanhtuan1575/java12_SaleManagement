package java12.projectsalemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java12.projectsalemanagement.upload.FileStorageProperties;


//@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableWebMvc
public class ProjectSaleManagementApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(ProjectSaleManagementApplication.class, args);
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }


}
