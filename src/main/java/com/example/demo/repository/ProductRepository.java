package com.example.demo.repository;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
@Repository
@ComponentScan(basePackages = "com.example.demo")
public interface ProductRepository extends JpaRepository<Product, Long> {
	//商品名の部分一致検索
	List<Product> findByNameContaining(String name);
	
	//価格範囲で検索
	List<Product>findByPriceBetween(double minPrice, double maxPrice);
	
	//在庫が指定以上の商品のみ取得
	List<Product>findByQuantityGreaterThanEqual(int minQuantity);
	
	//価格順で並べる
	List<Product> findByNameVontainingOrderByPriceAsc(String name);

}
