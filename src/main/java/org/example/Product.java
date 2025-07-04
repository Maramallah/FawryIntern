package org.example;


import java.time.LocalDate;

public class Product  {
    private String name;
    private double price;
    private int quantity;
    private  LocalDate expiryDate=null;


    public Product(String name, double price, int quantity , LocalDate expiryDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = expiryDate;


    }
    // Constructor for non-expirable products
    public Product(String name, double price, int quantity) {
        this(name, price, quantity, null);
    }




    // Getters
    public String getName() { return name; }

    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public  LocalDate getExpiryDate(){return expiryDate;}


    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }


    public boolean isExpired() {
        return  expiryDate != null && expiryDate.isBefore(LocalDate.now());
    }


}



