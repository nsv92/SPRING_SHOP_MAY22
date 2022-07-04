package simpleUsers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("contract.entities")
public class SimpleUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleUserServiceApplication.class, args);
    }
}
