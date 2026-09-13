package com.example.kubernetes.k8s.controller;


import com.example.kubernetes.k8s.model.OrderEntity;
import com.example.kubernetes.k8s.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class KubernetesController {

    private final OrderService orderService;

    @GetMapping("/message")
    public String getMessage() {
        return "Your Spring Application Deployed into the Kubernetes";
    }

    @PostMapping("/order")
    public OrderEntity saveOder(@RequestBody OrderEntity order) {
        return orderService.order(order);
    }

    @GetMapping("/order/{id}")
    public OrderEntity getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }

}