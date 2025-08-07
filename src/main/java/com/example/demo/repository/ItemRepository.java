package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByNameContainingAndPriceLessThanEqual(String keyword, Integer maxPrice);

	List<Item> findByNameContaining(String keyword);

	List<Item> findByPriceLessThanEqual(Integer maxPrice);

	List<Item> findByCategoryId(Integer categoryId);

}