package com.example.demo.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String showEditForm(@PathVariable Long id, Model model) {
	    Optional<Product> product = productRepository.findById(id);
	    if (product != null) {
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
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
	
	@PostMapping("/update/{id}")
	public String pudateProduct(@PathVariable Long id, @ModelAttribute Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if(existingProduct.isPresent()) {
		Product updatedProduct = existingProduct.get();
		   updatedProduct.setName(product.getName());
	        updatedProduct.setQuantity(product.getQuantity());
	        updatedProduct.setPrice(product.getPrice());
	        productRepository.save(updatedProduct);
	    }
	    return "redirect:/";
	}
	
	@GetMapping("/search")
	public String searchProducts(@RequestParam(required = false) String name,
	        @RequestParam(required = false) Double minPrice,
	        @RequestParam(required = false) Double maxPrice,
	        @RequestParam(required = false) Integer minQuantity, Model model) {
		  // 検索条件によってリストを切り替える
		
		 List<Product> products;
		
        if (name != null && !name.isEmpty()) {
            products = productRepository.findByNameContaining(name);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceBetween(minPrice, maxPrice);
        } else if (minQuantity != null) {
            products = productRepository.findByQuantityGreaterThanEqual(minQuantity);
        } else {
            products = productRepository.findAll(); // 全件取得
        }
        
        model.addAttribute("products", products);
        return "product-list";
	}
	
		
}
	
