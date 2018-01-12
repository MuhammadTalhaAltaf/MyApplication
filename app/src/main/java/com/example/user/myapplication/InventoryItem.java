package com.example.user.myapplication;

/**
 * Created by USER on 07-Jan-18.
 */

public class InventoryItem {

    String itemId;
    String itemName;
    String itemStock;
    String itemSupplier;
    String itemPrice;

    public InventoryItem(){

    }

    public InventoryItem(String itemId,  String itemName, String itemStock, String itemSupplier, String itemPrice){

        this.itemId = itemId;
        this.itemName = itemName;
        this.itemStock = itemStock;
        this.itemSupplier = itemSupplier;
        this.itemPrice = itemPrice;
    }
    public String getItemId(){
        return itemId;
    }

    public String getItemName(){
        return itemName;
    }

    public String getItemStock(){
        return itemStock;
    }

    public String getItemSupplier(){
        return itemSupplier;
    }

    public String getItemPrice(){ return itemPrice;}

}
