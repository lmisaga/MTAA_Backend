package com.sclad.scladapp.repository;


import com.sclad.scladapp.entity.RestockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestockOrderRepository extends JpaRepository<RestockOrder, Long> {
}
