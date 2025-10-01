package service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import model.*;
import repository.*;

public class RestaurantService{
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public String registerRestaurant(String name, String gst, String email, String phoneNumber) {
        Restaurant restaurant = new Restaurant(name, gst, email, phoneNumber);
        restaurantRepository.save(restaurant);
        System.out.println("Registered!!! RestaurantId: " + restaurant.getRestaurantId());
        return restaurant.getRestaurantId();
    }

    public void addItemToCatalog(String restaurantName, String itemName, double price, int quantity) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) throw new RuntimeException("Restaurant not found");
        restaurant.getCatalog().put(itemName, new Item(itemName, price, quantity));
        System.out.println(getCatalogItems(restaurantName));
    }

    public List<Item> searchItem(String restaurantName, String itemName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) throw new RuntimeException("Restaurant not found");

        List<Item> result = new ArrayList<>();
        for (Item item : restaurant.getCatalog().values()) {
            if (item.getName().equalsIgnoreCase(itemName) && item.getQuantity() > 0) {
                result.add(item);
            }
        }
        result.sort(Comparator.comparingDouble(Item::getPrice));

        return result;
    }

    public List<String> getCatalogItems(String restaurantName) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantName);
        if (restaurant == null) throw new RuntimeException("Restaurant not found");

        List<String> names = new ArrayList<>();
        for (Item item : restaurant.getCatalog().values()) {
            names.add(item.getName());
            System.err.println(item.getName() + " " + item.getQuantity());
        }


        return names;
    }
}
