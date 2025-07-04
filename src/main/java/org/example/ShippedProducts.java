package org.example;

import java.time.LocalDate;

public class ShippedProducts extends Product implements Shipable{
   private double weight;

    public ShippedProducts(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }
    public ShippedProducts(String name, double price, int quantity, double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
      return  weight;
    }
}
