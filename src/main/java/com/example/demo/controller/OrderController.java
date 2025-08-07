package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.model.Cart;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class OrderController {
	@Autowired
	Account account;

	@Autowired
	Cart cart;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;

	@GetMapping("/order")
	public String index(Model model) {
		User user = userRepository.findById(account.getId()).get();
		model.addAttribute("user", user);

		return "orderConfirm";
	}

	@PostMapping("/order")
	public String order(
			@RequestParam("address") String address,
			@RequestParam("cardNumber") String cardNumber,
			@RequestParam("securityCode") String securityCode,
			@RequestParam("cardLimit") LocalDate cardLimit,
			Model model) {
		Order order = new Order(
				account.getId(),
				LocalDate.now(),
				cart.getTotalPrice(),
				address,
				cardNumber,
				securityCode,
				cardLimit);
		orderRepository.save(order);

		List<Item> itemList = cart.getItems();
		List<OrderDetail> orderDetails = new ArrayList<>();
		for (Item item : itemList) {
			orderDetails.add(
					new OrderDetail(
							order.getId(),
							item.getId(),
							item.getQuantity()));
		}
		orderDetailRepository.saveAll(orderDetails);

		cart.clear();

		model.addAttribute("orderNumber", order.getId());

		return "ordered";
	}
}