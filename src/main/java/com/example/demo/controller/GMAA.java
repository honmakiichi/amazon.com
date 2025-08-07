package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.Account;

@ControllerAdvice
public class GMAA {
	@Autowired
	private Account account;

	@ModelAttribute
	public void addCommonAttributes(Model model) {
		model.addAttribute("currentDate", LocalDate.now());
	}
}