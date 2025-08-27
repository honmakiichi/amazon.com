package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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

	private static final int PASSWORD_MIN = 6;
	private static final int PASSWORD_MAX = 64;

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
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
			return "login";
		}
		if (!user.getPassword().equals(password)) {
			model.addAttribute("error", "メールアドレスまたはパスワードが間違っています");
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

		List<String> errors = new ArrayList<>();
		if (email == null || email.isBlank()) {
			errors.add("メールアドレスを入力してください");
		}
		if (name == null || name.isBlank()) {
			errors.add("名前を入力してください");
		}
		if (password == null || password.isBlank()) {
			errors.add("パスワードを入力してください");
		}
		if (confirmpassword == null || confirmpassword.isBlank()) {
			errors.add("確認用パスワードを入力してください");
		}
		if (password != null && !password.isBlank()) {
			int len = password.length();
			if (len < PASSWORD_MIN || len > PASSWORD_MAX) {
				errors.add("パスワードは6文字以上64文字以下で入力してください");
			}
		}
		if (password != null && confirmpassword != null && !password.equals(confirmpassword)) {
			errors.add("パスワードが一致していません");
		}
		if (!errors.isEmpty()) {
			model.addAttribute("errors", errors);
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
