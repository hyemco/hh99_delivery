package com.hh99.delivery.repository;

import com.hh99.delivery.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}