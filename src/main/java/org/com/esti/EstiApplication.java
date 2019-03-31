package org.com.esti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EstiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EstiApplication.class, args);
    }

}
