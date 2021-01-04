package zl.com.test.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SupervisonInterfaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupervisonInterfaceApplication.class, args);
    }

}
