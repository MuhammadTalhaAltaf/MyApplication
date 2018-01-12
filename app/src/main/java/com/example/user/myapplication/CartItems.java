package com.example.user.myapplication;

/**
 * Created by USER on 06-Jan-18.
 */

public class CartItems {
    public String name;
    public Integer price;
    public Integer quantity;

    public CartItems(){
super();
}

    public CartItems(String name,Integer pr,Integer quan){
        super();
        this.name=name;
        this.quantity=quan;
        this.price=pr;
    }
    public String toString() {
        return  this.name + " [$" + this.price + "] "+this.quantity;
    }
}
