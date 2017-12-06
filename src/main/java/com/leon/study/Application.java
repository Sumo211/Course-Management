package com.leon.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Delegate the client side caching using Etag and If-None-Match header to Spring
    /*@Bean
    public Filter etagFilter() {
        return new ShallowEtagHeaderFilter();
    }*/

}
