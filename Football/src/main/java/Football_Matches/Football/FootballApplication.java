package Football_Matches.Football;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class FootballApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootballApplication.class, args);
        System.out.println("welcome football");
    }

}
