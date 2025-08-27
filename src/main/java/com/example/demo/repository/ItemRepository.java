package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByPriceLessThanEqual(Integer maxPrice);

	List<Item> findByCategoryId(Integer categoryId);

	List<Item> findByNameContainingIgnoreCaseAndPriceLessThanEqual(String keyword, Integer maxPrice);

	List<Item> findByNameContainingIgnoreCase(String keyword);

}