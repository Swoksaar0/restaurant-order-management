import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderId;
    private List<MenuItem> items;
    private double totalAmount;

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
        totalAmount += item.getPrice();
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
        totalAmount -= item.getPrice();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    // Display the order details
    public void showOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Items in the order:");
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total Amount: $" + totalAmount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Override
    public String toString() {
        return "Order{orderId=" + orderId + ", items=" + items + ", totalAmount=" + totalAmount + "}";
    }
}
