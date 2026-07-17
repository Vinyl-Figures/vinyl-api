package br.com.acta.vinylpgapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class VinylPgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VinylPgApiApplication.class, args);
    }

}
