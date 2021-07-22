package com.techelevator;

public class Product {
    private String slot;
    private String name;
    private double price;
    private int quantity;

    public Product (String slot, String name, double price, int quantity){
        this.slot = slot;
        this.name = name;
        this.price = price;
        this.quantity = quantity;


    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void decreaseQuanity() {
        quantity--;
    }
//    public String soldOut() {
//        if (quantity == 0) {
//            return "sold out!!";
//        }
//        return null;
//    }

}
