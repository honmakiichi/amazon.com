package com.example.demo.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Item;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {
	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ItemRepository itemRepository;

	@GetMapping("/items")
	public String index(
			@RequestParam(value = "categoryId", required = false) Integer categoryId,
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "maxPrice", required = false) Integer maxPrice,
			@RequestParam(value = "sort", required = false) String sort,
			Model model) {
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		List<Item> itemList = null;
		if (keyword.length() > 0 && maxPrice != null) {
			itemList = itemRepository.findByNameContainingIgnoreCaseAndPriceLessThanEqual(keyword, maxPrice);
		} else if (keyword.length() > 0) {
			itemList = itemRepository.findByNameContainingIgnoreCase(keyword);
		} else if (maxPrice != null) {
			itemList = itemRepository.findByPriceLessThanEqual(maxPrice);
		} else if (categoryId != null) {
			itemList = itemRepository.findByCategoryId(categoryId);
		} else {
			itemList = itemRepository.findAll();
		}

		if ("priceAsc".equals(sort)) {
			itemList.sort(Comparator.comparing(Item::getPrice));
		} else if ("priceDesc".equals(sort)) {
			itemList.sort(Comparator.comparing(Item::getPrice).reversed());
		}

		model.addAttribute("items", itemList);
		model.addAttribute("sort", sort);

		return "items";
	}
}