package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.logic.ProductService;
import com.example.demo.model.Product;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor // ProductServiceを初期化するコンストラクタの自動生成
public class ProductController {
	private final ProductService productService;
	                            
	@GetMapping
	public String listProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "product-list";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("product", new Product());
		return "add-product";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
}
