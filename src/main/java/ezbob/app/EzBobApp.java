package ezbob.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ezbob.controllers","ezbob.services"})
public class EzBobApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(EzBobApp.class, args);
    }

}
