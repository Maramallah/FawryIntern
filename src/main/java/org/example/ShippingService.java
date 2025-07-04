package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class ShippingService {

    public void shipItems(List<Shipable> items) {
        if (items.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        // Count quantities for each product
        Map<String, Integer> productCounts = new HashMap<>();
        Map<String, Double> productWeights = new HashMap<>();

        for (Shipable item : items) {
            String name = item.getName();
            double weight = item.getWeight();

            productCounts.put(name, productCounts.getOrDefault(name, 0) + 1);
            productWeights.put(name, weight);
            totalWeight += weight;
        }


        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = productWeights.get(name);

            System.out.printf("%dx %s %.0fg\n", count, name, weight * count);
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight / 1000.0);
    }

    // Helper method to collect shippable items from cart
    public List<Shipable> collectShippableItems(Cart cart) {
        List<Shipable> shippableItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Shipable) {
                // Add multiple instances for each quantity
                for (int i = 0; i < quantity; i++) {
                    shippableItems.add((Shipable) product);
                }
            }
        }

        return shippableItems;
    }

    // Calculate total shipping weight
    public double calculateShippingWeight(Cart cart) {
        double totalWeight = 0;

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof Shipable) {
                Shipable shippableProduct = (Shipable) product;
                totalWeight += shippableProduct.getWeight() * quantity;
            }
        }

        return totalWeight / 1000.0; // Convert to kg
    }
}
