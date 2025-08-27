package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	@Query("""
			  SELECT od
			    FROM OrderDetail od
			    JOIN FETCH od.item
			   WHERE od.orderId = :orderId
			ORDER BY od.id ASC
			  """)
	List<OrderDetail> findByOrderIdWithItems(Integer orderId);
}