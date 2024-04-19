package com.sport.service.impl;

import com.sport.entity.OrderEntity;
import com.sport.repository.OrderRepository;
import com.sport.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<OrderEntity> findAll(Integer pageNo, Integer quantity) {
        Pageable pageable = PageRequest.of(pageNo - 1, quantity);
        return orderRepository.findAll(pageable);
    }
}
