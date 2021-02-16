package com.home.fitness;

import com.home.fitness.security.DisableAccessWarning;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessApplication {

    public static void main(String[] args) {
        DisableAccessWarning dis = new DisableAccessWarning();
        dis.disableAccessWarnings();
        SpringApplication.run(FitnessApplication.class, args);
    }
// Web Flux https://www.youtube.com/watch?v=77-wOZs2nPE&ab_channel=letsCode
// Web Flux JWT https://www.youtube.com/watch?v=GYQGaEC9Hog&ab_channel=letsCode
}
