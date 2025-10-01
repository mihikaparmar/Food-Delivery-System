package model;

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void reduceQuantity(int qty) {
        this.quantity -= qty;
    }

    public void increaseQuantity(int qty) {
        this.quantity += qty;
    }
}
