package org.sanaa.youcode.redline.unirent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.sanaa.youcode.redline.unirent")
public class UniRentApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniRentApplication.class, args);
    }

}
