package com.example.kubernetes.k8s.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KubernetesController {

    @GetMapping("/message")
    public String getMessage() {
        return "Deployed Spring Application into the Kubernetes Pod";
    }
}