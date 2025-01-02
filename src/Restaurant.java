import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private String name;
    private List<MenuItem> menu;
    private List<Order> orders;

    public Restaurant(String name) {
        this.name = name;
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item);
    }

    public Order createOrder(int orderId) {
        Order newOrder = new Order(orderId);
        orders.add(newOrder);
        return newOrder;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public List<Order> getOrders() {
        return orders;
    }

    // Filter menu items by category
    public List<MenuItem> filterMenuByCategory(String category) {
        return menu.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    // Search for an order by its ID
    public Order findOrderById(int id) {
        return orders.stream()
                .filter(order -> order.getOrderId() == id)
                .findFirst()
                .orElse(null);
    }

    // Sort menu items by price
    public void sortMenuByPrice() {
        menu.sort(Comparator.comparingDouble(MenuItem::getPrice));
    }

    // Sort orders by total amount
    public void sortOrdersByTotalAmount() {
        orders.sort(Comparator.comparingDouble(Order::getTotalAmount));
    }

    @Override
    public String toString() {
        return "Restaurant{name='" + name + "', menu=" + menu + ", orders=" + orders + "}";
    }
}


