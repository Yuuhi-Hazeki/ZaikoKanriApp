package com.example.demo.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Integer id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private int price;
	
}
