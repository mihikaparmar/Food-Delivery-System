package model;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Restaurant {
    private String restaurantId;
    private String name;
    private String gstNumber;
    private String email;
    private String phoneNumber;
    private Map<String, Item> catalog;

    public Restaurant(String name, String gstNumber, String email, String phoneNumber) {
        this.restaurantId = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        this.name = name;
        this.gstNumber = gstNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.catalog = new HashMap<>();
    }

    public String getRestaurantId() { return restaurantId; }
    public String getName() { return name; }
    public Map<String, Item> getCatalog(){ return catalog; }
}
