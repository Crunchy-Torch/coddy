package org.superdev.coddy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        new Main().configure(new SpringApplicationBuilder(Main.class)).run(args);
    }
}
