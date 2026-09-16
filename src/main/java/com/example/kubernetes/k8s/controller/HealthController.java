package com.example.kubernetes.k8s.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/internal/health")
public class HealthController {

  private final  ApplicationEventPublisher publisher;


    @PostMapping("/liveness/down")
    public String livenessDown() {

        AvailabilityChangeEvent.publish(
                publisher,
                this,
                LivenessState.BROKEN
        );

        return "Liveness changed to DOWN";
    }

    @PostMapping("/readiness/down")
    public String readinessDown() {

        AvailabilityChangeEvent.publish(
                publisher,
                this,
                ReadinessState.REFUSING_TRAFFIC
        );

        return "Readiness changed to DOWN";
    }

    @PostMapping("/readiness/up")
    public String readinessUp() {

        AvailabilityChangeEvent.publish(
                publisher,
                this,
                ReadinessState.ACCEPTING_TRAFFIC
        );

        return "Readiness changed to UP";
    }

}
