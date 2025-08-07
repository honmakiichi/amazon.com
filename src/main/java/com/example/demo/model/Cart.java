package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Item;

@Component
@SessionScope
public class Cart {
	private List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}

	public int getTotalPrice() {
		int total = 0;
		for (Item item : items) {
			total += item.getPrice() * item.getQuantity();
		}
		return total;
	}

	public void add(Item newItem) {
		Item existsItem = null;

		for (Item item : items) {
			if (item.getId() == newItem.getId()) {
				existsItem = item;
				break;
			}
		}

		if (existsItem == null) {
			items.add(newItem);
		} else {
			existsItem.setQuantity(
					existsItem.getQuantity() + newItem.getQuantity());
		}
	}

	public void delete(int itemId) {
		for (Item item : items) {
			if (item.getId() == itemId) {
				items.remove(item);
				break;
			}
		}
	}

	public void clear() {
		items = new ArrayList<>();
	}
}