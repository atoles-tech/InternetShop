package shop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import shop.repository.ProductRepository;
import shop.repository.SupplierRepository;
import shop.repository.UserRepository;

@SpringBootApplication
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ProductRepository repo,SupplierRepository sup, UserRepository userRepo) {
		return args -> {};
	}

}
