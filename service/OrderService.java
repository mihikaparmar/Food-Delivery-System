package service;
import repository.*;
import model.*;
import java.util.List;



public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    public String placeOrder(String userId, String restaurantName, String itemName, int quantity) {
        User user = userRepository.findById(userId);
        if (user == null) throw new RuntimeException("User not found");

        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) throw new RuntimeException("Restaurant not found");

        Item foundItem = null;
        for (Item item : restaurant.getCatalog().values()) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                foundItem = item;
                break;
            }
        }
        if (foundItem == null) throw new RuntimeException("Item not found");
        if (foundItem.getQuantity() < quantity) throw new RuntimeException("Insufficient stock");

        foundItem.reduceQuantity(quantity);
        Order order = new Order(userId, restaurantName, itemName, quantity);
        orderRepository.save(order);
        System.out.println("Order placed successfully orderId: " + order.getOrderId());
        return order.getOrderId();
    }

    public List<Order> getOrders(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public void cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId);
        if (order == null) throw new RuntimeException("Order not found");
        order.cancel();
        System.out.println("Order " + orderId + " canceled successfully.");
    }
}
