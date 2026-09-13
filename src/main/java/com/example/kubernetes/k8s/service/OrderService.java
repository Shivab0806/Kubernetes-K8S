package com.example.kubernetes.k8s.service;

import com.example.kubernetes.k8s.model.OrderEntity;
import com.example.kubernetes.k8s.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderEntity order(OrderEntity order){
      return  orderRepository.save(order) ;
    }

    public String delete(Long id){
        orderRepository.deleteById(id);
        return "Deleted Successfully";
    }

    public OrderEntity findById(Long id ){
        return  orderRepository.findById(id).get() ;
    }
    
}
