package com.geekbrains.geekmarketwinter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GeekMarketWinterApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(GeekMarketWinterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		List<ProductDTO> products = repository.getProducts(1).stream().collect(toCollection(ArrayList::new));
//		System.out.println(products);
	}
}