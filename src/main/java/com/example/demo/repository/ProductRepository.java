package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	//商品名の部分一致検索
	List<Product> findByNameContainingIgnoreCase(String name);
	
	//価格範囲で検索
	List<Product>findByPriceBetween(double minPrice, double maxPrice);
	
	//在庫が指定以上の商品のみ取得
	List<Product>findByQuantityGreaterThanEqual(int minQuantity);
	
	//価格順で並べる
	List<Product> findByNameVontainingOrderByPriceAsc(String name);

}
