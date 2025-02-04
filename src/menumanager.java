import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class menumanager {
    public void addMenuItem(String name, double price, String category) {
        try (Connection conn = dbconnection.getConnection()) {
            String query = "INSERT INTO menu_item (name, price, category) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setDouble(2, price);
                stmt.setString(3, category);
                stmt.executeUpdate();
                System.out.println("Menu item added successfully.");
            }
        } catch (SQLException e) {
            System.err.println("Databas error: " + e.getMessage());
        }
    }

    public void deleteMenuItem(int itemId) {
        try (Connection conn = dbconnection.getConnection()) {
            String query = "DELETE FROM menu_item WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, itemId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Menu item deleted successfully.");
                } else {
                    System.out.println("Item not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}