package model;
import java.util.UUID;

public class Order {
    private String orderId;
    private String userId;
    private String restaurantName;
    private String itemName;
    private int quantity;
    private OrderStatus status;

    public Order(String userId, String restaurantName, String itemName, int quantity) {
        this.orderId = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        this.userId = userId;
        this.restaurantName = restaurantName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.status = OrderStatus.CONFIRMED;
    }

    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public String getRestaurantName() { return restaurantName; }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }
    public OrderStatus getStatus() { return status; }

    public void cancel() {
        this.status = OrderStatus.CANCELLED;
    }
}
