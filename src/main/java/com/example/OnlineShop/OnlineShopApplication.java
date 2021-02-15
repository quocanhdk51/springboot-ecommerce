package com.example.OnlineShop;

import com.example.OnlineShop.models.Brand;
import com.example.OnlineShop.models.ERole;
import com.example.OnlineShop.models.Product;
import com.example.OnlineShop.models.Role;
import com.example.OnlineShop.repository.ProductRepository;
import com.example.OnlineShop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineShopApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Role role1 = new Role(ERole.ROLE_USER);
		Role role2 = new Role(ERole.ROLE_MODERATOR);
		Role role3 = new Role(ERole.ROLE_ADMIN);

		roleRepository.save(role1);
		roleRepository.save(role2);
		roleRepository.save(role3);

		Product product1 = new Product();
		product1.setTitle("Motul 300v 10W40");
		product1.setImage("https://www.upsieutoc.com/images/2021/01/22/motul.jpg");
		product1.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product1.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product1.setQuantity(100);
		product1.setBrand(Brand.MOTUL);
		product1.setPrice(101);

		Product product2 = new Product();
		product2.setTitle("Ipone Katana 10W40");
		product2.setImage("https://www.upsieutoc.com/images/2021/01/22/ipone.jpg");
		product2.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product2.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product2.setQuantity(100);
		product2.setBrand(Brand.IPONE);
		product2.setPrice(102);

		Product product3 = new Product();
		product3.setTitle("Fuchs Silkolene Pro 4 10W40");
		product3.setImage("https://www.upsieutoc.com/images/2021/01/22/fuchs.jpg");
		product3.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product3.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product3.setQuantity(100);
		product3.setBrand(Brand.FUCHS);
		product3.setPrice(103);

		Product product4 = new Product();
		product4.setTitle("Repsol Moto Racing 10W40");
		product4.setImage("https://www.upsieutoc.com/images/2021/01/22/repsol.png");
		product4.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product4.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product4.setQuantity(100);
		product4.setBrand(Brand.REPSOL);
		product4.setPrice(104);

		Product product5 = new Product();
		product5.setTitle("Liqui Moly Scooter Race 10W40");
		product5.setImage("https://www.upsieutoc.com/images/2021/01/22/liqui_moly.jpg");
		product5.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product5.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product5.setQuantity(100);
		product5.setBrand(Brand.LIQUI_MOLY);
		product5.setPrice(105);

		Product product6 = new Product();
		product6.setTitle("Total Quartz 9000 5W40");
		product6.setImage("https://www.upsieutoc.com/images/2021/01/22/total.jpg");
		product6.setDescription("How to and tutorial videos of cool CSS effect, Web Design ideas,JavaScript libraries, Node.");
		product6.setContent("Welcome to our channel Dev AT. Here you can learn web designing, UI/UX designing, html css tutorials, css animations and css effects, javascript and jquery tutorials and related so on.");
		product5.setQuantity(100);
		product6.setBrand(Brand.TOTAL);
		product6.setPrice(106);

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);
		productRepository.save(product4);
		productRepository.save(product5);
		productRepository.save(product6);
	}
}
