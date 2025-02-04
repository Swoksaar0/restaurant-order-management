import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderManager {
    public void createOrder(int orderId) {
        try (Connection conn = dbconnection.getConnection()) {
            String query = "INSERT INTO orders (order_id, order_details, total_amount) VALUES (?, '', 0) ON CONFLICT (order_id) DO NOTHING";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, orderId);
                stmt.executeUpdate();
                System.out.println("Order created successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void displayOrder(int orderId) {
        try (Connection conn = dbconnection.getConnection()) {
            String query = "SELECT order_details, total_amount FROM orders WHERE order_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, orderId);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Your Order:");
                        System.out.println("Items: " + rs.getString("order_details"));
                        System.out.println("Total Amount: $" + rs.getDouble("total_amount"));
                    } else {
                        System.out.println("Order not found.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
