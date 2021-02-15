package com.example.OnlineShop.controllers;

import com.example.OnlineShop.models.Product;
import com.example.OnlineShop.payload.request.ProductRequest;
import com.example.OnlineShop.payload.response.MessageResponse;
import com.example.OnlineShop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("")
    public Product getProductByID(@RequestParam long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseGet(productOptional::orElseThrow);

        return product;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        if (productRepository.existsByTitle(productRequest.getTitle()))
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Product already exists"));
        Product product = new Product(productRequest.getTitle(),
                                        productRequest.getImage(),
                                        productRequest.getBrand(),
                                        productRequest.getDescription(),
                                        productRequest.getContent(),
                                        productRequest.getPrice(),
                                        productRequest.getQuantity());

        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("New Product Added"));
    }

    @PutMapping("/{id}/edit")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@PathVariable("id") long id, @Valid @RequestBody ProductRequest productRequest) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseGet(productOptional::orElseThrow);

        product.setTitle(productRequest.getTitle());
        product.setImage(productRequest.getImage());
        product.setBrand(productRequest.getBrand());
        product.setDescription(productRequest.getDescription());
        product.setContent(productRequest.getContent());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());

        productRepository.save(product);

        return ResponseEntity.ok(new MessageResponse("Product updated"));
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Product deleted"));
    }
}
