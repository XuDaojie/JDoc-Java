package io.github.xudaojie.jdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by xdj on 2017/5/18.
 */
@SpringBootApplication
@ImportResource("classpath:/applicationContext.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
