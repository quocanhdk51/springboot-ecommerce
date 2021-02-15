package com.example.OnlineShop.controllers;

import com.example.OnlineShop.models.Cart;
import com.example.OnlineShop.models.Item;
import com.example.OnlineShop.models.Product;
import com.example.OnlineShop.models.User;
import com.example.OnlineShop.payload.request.ItemRequest;
import com.example.OnlineShop.payload.response.MessageResponse;
import com.example.OnlineShop.repository.CartRepository;
import com.example.OnlineShop.repository.ItemRepository;
import com.example.OnlineShop.repository.ProductRepository;
import com.example.OnlineShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Cart getCartByUserID(@RequestParam("user_id") long id) {
        return cartRepository.getCartByUserID(id);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> addNewItemToCart(@RequestParam("user_id") long userId,
                                              @Valid @RequestBody ItemRequest itemRequest) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseGet(userOptional::orElseThrow);

        Optional<Product> productOptional = productRepository.findById(itemRequest.getProductId());
        Product product = productOptional.orElseGet(productOptional::orElseThrow);

        if (product.getQuantity() < itemRequest.getQuantity())
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Exceeding the quantity in store"));

        Cart cart = user.getCart();

        Collection<Item> items = cart.getItems();

        for (Item item : items) {
            if (item.getProduct().getId() == itemRequest.getProductId()) {
                item.setQuantity(itemRequest.getQuantity());
                itemRepository.save(item);
                return ResponseEntity.ok(new MessageResponse("Successfully added into cart"));
            }
        }

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(itemRequest.getQuantity());

        product.getItems().add(item);

        productRepository.save(product);

        cart.getItems().add(itemRepository.findTopByOrderByIdDesc());

        cartRepository.save(cart);

        return ResponseEntity.ok(new MessageResponse("Successfully added into cart"));
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeItemByUserID(@RequestParam("user_id") long userId, @RequestParam("item_id") long itemId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseGet(userOptional::orElseThrow);

        Cart cart = user.getCart();

        Collection<Item> items = cart.getItems();

        for (Item item : items) {
            if (item.getId() == itemId) {
                cart.getItems().remove(item);
                item.getProduct().getItems().remove(item);
                item.setProduct(null);
                cartRepository.save(cart);
                return ResponseEntity.ok(new MessageResponse("Deleted from the cart"));
            }
        }

        return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error while deleting from cart"));
    }
}
