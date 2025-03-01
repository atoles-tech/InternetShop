package shop;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import shop.repository.ProductRepository;
import shop.repository.SupplierRepository;

// import shop.model.Supplier;
// import shop.model.Product;


@SpringBootApplication
public class PetShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetShopApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(ProductRepository repo,SupplierRepository sup) {
		return args -> {
			/*Supplier s1 = new Supplier("S1","adress1"); // test data launch only one time
			Supplier s2 = new Supplier("S2","adress2");
			Product p1 = new Product("iphone",100.,s1);
			Product p2 = new Product("xiaomi",90.,s1);
			Product p3 = new Product("infinix",99.,s2);
			Product p4 = new Product("realme",999.,s2);

			sup.save(s1);
			sup.save(s2);

			repo.save(p1);
			repo.save(p2);
			repo.save(p3);
			repo.save(p4);*/
		};
	}

}
