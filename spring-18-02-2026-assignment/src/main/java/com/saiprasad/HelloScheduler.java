package com.saiprasad;



import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HelloScheduler {

    private int count = 1;

    @Scheduled(fixedRate = 10000) // 10000 ms = 10 seconds
    public void printMessage() {

        if (count <= 10) {
            System.out.println("Hello Everybody");
            System.out.println("Number: " + count);
            count++;
        }
    }
}
