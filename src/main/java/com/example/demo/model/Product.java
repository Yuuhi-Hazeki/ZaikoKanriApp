package com.example.demo.model;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // LombokによるGetter、Setter、toString、equals、hashCodeの自動生成
@NoArgsConstructor // 引数なしコンストラクタ
@AllArgsConstructor // 全フィールドを含むコンストラクタ

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private int quantity;
	
	private int price;
	 @Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	 
	 @PrePersist
	    protected void onCreate() {
	        createdAt = LocalDateTime.now();
	    }
}
