import model.Item;
import model.Order;
import repository.OrderRepository;
import repository.RestaurantRepository;
import repository.UserRepository;
import service.OrderService;
import service.RestaurantService;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository();
        OrderRepository orderRepository = new OrderRepository();

        // Inject dependencies
        

        UserService userService = new UserService(userRepository);
        RestaurantService restaurantService = new RestaurantService(restaurantRepository);
        OrderService orderService = new OrderService(orderRepository, userRepository, restaurantRepository);

// -------------------------
        // Restaurant Registration
        // -------------------------
        System.out.println("Restaurant Operations:");
        String restaurantId1 = restaurantService.registerRestaurant(
                "Donald", "GST10905804580", "Donald@mail.com", "1234567890");
        String restaurantId2 = restaurantService.registerRestaurant(
                "Daisy", "GST10905804581", "Daisy@mail.com", "0987654321");

        // -------------------------
        // Add Catalog for Donald
        // -------------------------
        restaurantService.addItemToCatalog("Donald", "Sandwich", 100.00, 4);
        restaurantService.addItemToCatalog("Donald", "Sandwich", 100.00, 4);
        restaurantService.addItemToCatalog("Donald", "Burger", 250.00, 2);
        restaurantService.addItemToCatalog("Donald", "Pizza", -500.00, 10);

        // -------------------------
        // Add Catalog for Daisy
        // -------------------------
        restaurantService.addItemToCatalog("Daisy", "Fries", 80.00, 5);
        restaurantService.addItemToCatalog("Daisy", "Veg Wrap", 150.00, 3);
        restaurantService.addItemToCatalog("Daisy", "Ice Cream", 60.00, 0); // quantity 0, should not show in search

        restaurantService.getCatalogItems("Daisy");

        // -------------------------
        // Customer Registration
        // -------------------------
        System.out.println("Customer Operations:");
        String userId1 = userService.registerUser("User1", "user1@mail.com", "1234567890");
        String userId2 = userService.registerUser("User2", "user2@mail.com", "0987654321");
        String userId3 = userService.registerUser("User3", "user3@mail.com", "5555555555");

        // -------------------------
        // Search Item - Donald
        // -------------------------
        System.out.println("Search Results in Donald for 'Sandwich':");
        for (Item item : restaurantService.searchItem("Donald", "Sandwich")) {
            System.out.println("\"" + item.getName() + "\", " + item.getPrice() + ", " + item.getQuantity());
        }

        // -------------------------
        // Search Item - Daisy
        // -------------------------
        System.out.println("Search Results in Daisy for 'Ice Cream': (should not appear due to 0 quantity)");
        for (Item item : restaurantService.searchItem("Daisy", "Ice Cream")) {
            System.out.println("\"" + item.getName() + "\", " + item.getPrice() + ", " + item.getQuantity());
        }

        // -------------------------
        // Place Orders
        // -------------------------
        String orderId1 = orderService.placeOrder(userId1, "Donald", "Sandwich", 2);
        String orderId2 = orderService.placeOrder(userId2, "Donald", "Pizza", 1);
        String orderId3 = orderService.placeOrder(userId3, "Daisy", "Fries", 2);

        restaurantService.getCatalogItems("Donald");



        // -------------------------
        // Cancel Order
        // -------------------------
        orderService.cancelOrder(orderId1); // cancel User1's Sandwich order
       // orderService.cancelOrder("123"); 

        // -------------------------
        // Get Orders for each user
        // -------------------------
        System.out.println("Orders for User1:");
        for (Order order : orderService.getOrders(userId1)) {
            System.out.println(order.getOrderId() + ", \"" +
                    order.getItemName() + "\", " + order.getQuantity() + ", " + order.getStatus());
        }

        System.out.println("Orders for User2:");
        for (Order order : orderService.getOrders(userId2)) {
            System.out.println(order.getOrderId() + ", \"" +
                    order.getItemName() + "\", " + order.getQuantity() + ", " + order.getStatus());
        }

        System.out.println("Orders for User3:");
        for (Order order : orderService.getOrders("123")) {
            System.out.println(order.getOrderId() + ", \"" +
                    order.getItemName() + "\", " + order.getQuantity() + ", " + order.getStatus());
        }

        // -------------------------
        // Bonus: Cancel another order
        // -------------------------
        orderService.cancelOrder(orderId3); // cancel Daisy order

    }
}
