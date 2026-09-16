package com.example.kubernetes.k8s.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SlowStartup implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

       log.info("Simulating slow startup...");

        Thread.sleep(60000);

        log.info("Startup completed.");
    }
}
