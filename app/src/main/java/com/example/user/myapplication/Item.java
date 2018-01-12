package com.example.user.myapplication;

/**
 * Created by USER on 24-Dec-17.
 */

public class Item {

    String itemId;
    String itemName;
    String itemPrice;
    String itemDesc;

    public Item(){

    }

    public Item(String itemId, String itemName, String itemPrice, String itemDesc){

        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDesc = itemDesc;
    }

    public String getItemId(){
        return itemId;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemPrice(){
        return itemPrice;
    }

    public String getItemDesc(){
        return itemDesc;
    }
}
