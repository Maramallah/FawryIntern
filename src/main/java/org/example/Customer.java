package org.example;

public class Customer {
    String CustomerName;
    double Balance;
    Customer(String name , double balance){
        this.CustomerName=name;
        this.Balance=balance;
    }
public double getBalance(){
        return Balance;
}
    public void withdraw(double amount){
        if (Balance>= amount){
            this.Balance-=amount;
        }else{
            System.out.println("No Enough Balance ");
        }

    }

}
