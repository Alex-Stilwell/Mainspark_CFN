package mainspark.blog.springbasic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//http://localhost:8080/v3/api-docs
//http://localhost:8080/v3/api-docs.yaml
//http://localhost:8080/webjars/swagger-ui/index.html
@SpringBootApplication
public class SpringBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicApplication.class, args);
    }
}
