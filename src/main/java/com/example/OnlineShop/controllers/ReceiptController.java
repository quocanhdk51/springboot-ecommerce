package com.example.OnlineShop.controllers;

import com.example.OnlineShop.models.*;
import com.example.OnlineShop.payload.request.PaymentRequest;
import com.example.OnlineShop.payload.response.MessageResponse;
import com.example.OnlineShop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReceiptRepository receiptRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Receipt> getReceiptByUserID(@RequestParam("user_id") long id) {
        return receiptRepository.getReceiptByUserID(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> makePayment(@RequestParam("user_id") long id, @RequestParam("total") double total, @RequestBody Collection<PaymentRequest> paymentRequests) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseGet(userOptional::orElseThrow);

        Receipt receipt = new Receipt();

        receipt.setUser(user);
        user.getReceipts().add(receipt);

        for (PaymentRequest paymentRequest : paymentRequests) {
            Optional<Product> productOptional = productRepository.findById(paymentRequest.getId());
            Product product = productOptional.orElseGet(productOptional::orElseThrow);

            if (product.getQuantity() < paymentRequest.getCount())
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Exceeding the quantity in store"));

            Item item = new Item();
            item.setQuantity(paymentRequest.getCount());
            item.setProduct(product);

            product.setQuantity(product.getQuantity() - paymentRequest.getCount());
            product.getItems().add(item);

            productRepository.save(product);

            receipt.getItems().add(itemRepository.findTopByOrderByIdDesc());
        }

        receipt.setTotal(total);

        receiptRepository.save(receipt);

        return ResponseEntity.ok(new MessageResponse("Added to Receipt"));
    }
}
