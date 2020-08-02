package subscription;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("subscription.dao")
public class SubscriptionApplication {

    public static void main(String[] args) {

        SpringApplication.run(SubscriptionApplication.class, args);
    }
}
