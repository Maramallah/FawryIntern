package org.example;

import java.util.List;
import java.util.Map;

public class CheckOutService {

    public static void checkout(Customer customer, Cart cart) {
        ShippingService shippingService = new ShippingService();


        double subtotal = getSubtotal(cart);
        double shippingWeight = shippingService.calculateShippingWeight(cart);
        double shippingFee = calculateShipping(shippingWeight);
        double total = subtotal + shippingFee;


        if (!confirmOrder(customer, cart, total)) {
            System.out.println(" Order Denied");
            return;
        }


        List<Shipable> shippableItems = shippingService.collectShippableItems(cart);
        if (!shippableItems.isEmpty()) {
            shippingService.shipItems(shippableItems);
        }


        System.out.println("** Checkout receipt **");
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            product.reduceQuantity(quantity);
            double itemTotal = product.getPrice() * quantity;

            System.out.printf("%dx %s %.0f\n", quantity, product.getName(), itemTotal);
        }

        //  Deduct balance
        customer.withdraw(total);


        System.out.println("----------------------");
        System.out.printf("Subtotal %.0f\n", subtotal);
        System.out.printf("Shipping %.0f\n", shippingFee);
        System.out.printf("Amount %.0f\n", total);
        System.out.printf("Customer current balance after payment: %.0f\n", customer.getBalance());
        System.out.println("----------------------");
    }

    public static boolean confirmOrder(Customer customer, Cart cart
                                       , double total) {
        if (cart.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return false;
        }

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product.isExpired()) {
                System.out.println("Error: " + product.getName() + " is expired");
                return false;
            }

            if (product.getQuantity() < quantity) {
                System.out.println("Error: " + product.getName() + " is out of stock");
                return false;
            }
        }

        if (customer.getBalance() < total) {
            System.out.println("Error: Customer's balance is insufficient");
            return false;
        }

        return true;
    }

    public static double getSubtotal(Cart cart) {
        double subtotal = 0;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            subtotal += entry.getKey().getPrice() * entry.getValue();
        }
        return subtotal;
    }

    public static double calculateShipping(double weightInKg) {
        return weightInKg > 0 ? 30 : 0;  //  $30 shipping if weight > 0
    }
}
