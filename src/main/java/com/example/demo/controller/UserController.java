package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Account account;

	@GetMapping({ "/", "/logout" })
	public String index(HttpSession session) {
		session.invalidate();
		return "top";
	}

	@GetMapping({ "/login" })
	public String gologin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			model.addAttribute("error", "メールアドレスが間違っています");
			return "login";
		}

		if (!user.getPassword().equals(password)) {
			model.addAttribute("error", "パスワードが間違っています");
			return "login";
		}

		account.setName(user.getName());
		account.setId(user.getId());

		return "redirect:/items";
	}

	@GetMapping({ "/add" })
	public String goadd() {
		return "add";
	}

	@PostMapping("/add")
	public String gologinfromadd(
			@RequestParam String email,
			@RequestParam String name,
			@RequestParam String password,
			@RequestParam String confirmpassword,
			Model model) {
		if (email.isBlank() || name.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
			model.addAttribute("error", "すべての項目を入力してください");
			return "add";
		}

		if (!password.equals(confirmpassword)) {
			model.addAttribute("error", "パスワードが一致しません");
			return "add";
		}

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);

		userRepository.save(user);

		return "redirect:/login";
	}
}
