package repository;


import java.util.*;
import model.Order;

public class OrderRepository {

    private Map<String, List<Order>> ordersByUser = new HashMap<>();

    public void save(Order order) {
        List<Order> userOrders = ordersByUser.get(order.getUserId());
        if (userOrders == null) {
            userOrders = new ArrayList<>();
            ordersByUser.put(order.getUserId(), userOrders);
        }
        userOrders.add(order);

    }

    public List<Order> findByUserId(String userId) {
        return ordersByUser.getOrDefault(userId, new ArrayList<>());
    }

    public Order findById(String orderId) {
        for (List<Order> orders : ordersByUser.values()) {
            for (Order o : orders) {
                if (o.getOrderId().equals(orderId)) {
                    return o;
                }
            }
        }
        return null;
    }
}
