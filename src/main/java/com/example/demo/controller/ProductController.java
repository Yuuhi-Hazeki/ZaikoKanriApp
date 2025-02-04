package com.example.demo.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.logic.ProductService;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor // ProductServiceを初期化するコンストラクタの自動生成
public class ProductController {
	private final ProductService productService;
	@Autowired
	private ProductRepository productRepository;
	                            
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
	
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
	    Optional<Product> product = productRepository.findById(id);
	    if (product.isPresent()) {
	        model.addAttribute("product", product.get());
	        return "edit-product";
	    } else {
	        return "redirect:/"; // 商品が見つからない場合はリスト画面へリダイレクト
	    }
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute Product product) {
		productService.saveProduct(product);
		return "redirect:/";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Integer id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
}
