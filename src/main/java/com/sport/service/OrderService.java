package com.sport.service;

import com.sport.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Page<OrderEntity> findAll(Integer pageNo, Integer quantity);
}
