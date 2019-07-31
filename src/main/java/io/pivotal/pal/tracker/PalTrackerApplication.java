package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {


    @Value("${PORT:NOT SET}")
    String port;

    @Value("${MEMORY_LIMIT:NOT SET}")
    String memoryLimit;

    @Value("${CF_INSTANCE_INDEX:NOT SET}")
    String cfInstanceIndex;

    @Value("${CF_INSTANCE_ADDR:NOT SET}")
    String cfInstanceAddr;

    @Value("${WELCOME_MESSAGE}")
    String message;

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    public EnvController envController(){
        return new EnvController(port, memoryLimit, cfInstanceIndex , cfInstanceAddr);
    }

    @Bean
    public WelcomeController welcomeController(){
        return new WelcomeController(message);
    }


}
