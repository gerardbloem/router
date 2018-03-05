package router;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;


@SpringBootApplication

public class Application {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        Scenario scenario = Scenario.getInstance();
        scenario.aantalGbExtra(20);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("total time = " + TimeUnit.NANOSECONDS.toSeconds(totalTime) + " seconds");

        ctx.close();
    }
}