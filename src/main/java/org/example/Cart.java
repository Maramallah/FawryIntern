package org.example;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> shoppingCart;

    public Cart() {
        shoppingCart = new HashMap<>();
    }

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

           // if the product already exists in cart then will add its prev quntity to the one added now
           shoppingCart.put(product, shoppingCart.getOrDefault(product, 0) + quantity);

    }

    public Map<Product, Integer> getItems() {
        return shoppingCart;
    }

    public boolean isEmpty() {
        return shoppingCart.isEmpty();
    }

    public void clear() {
        shoppingCart.clear();
    }
}
