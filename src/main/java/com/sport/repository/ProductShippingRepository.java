package com.sport.repository;

import com.sport.entity.ProductShippingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductShippingRepository extends JpaRepository<ProductShippingEntity, Long> {
}
