import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql://localhost:2020/postgres";
        String username = "postgres";
        String password = "dima2345";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Quit");
            int role = scanner.nextInt();

            if (role == 1) {
                System.out.println("Choose action:\n1. Add new menu item\n2. Delete menu item\n3. Exit");
                int action = scanner.nextInt();

                if (action == 1) {
                    System.out.println("Name:");
                    String name = scanner.next();
                    System.out.println("Price:");
                    double price = scanner.nextDouble();
                    System.out.println("Category:");
                    String category = scanner.next();

                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        String query = "INSERT INTO menu_item (name, price, category) VALUES (?, ?, ?)";
                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                            stmt.setString(1, name);
                            stmt.setDouble(2, price);
                            stmt.setString(3, category);
                            stmt.executeUpdate();
                            System.out.println("Menu item added successfully.");
                        }
                    } catch (SQLException e) {
                        System.err.println("Database error: " + e.getMessage());
                    }

                } else if (action == 2) {
                    System.out.println("Item ID to delete:");
                    int itemId = scanner.nextInt();

                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        String query = "DELETE FROM menu_item WHERE id = ?";
                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
                } else if (action == 3) {
                    continue;
                }

            } else if (role == 2) {
                System.out.println("Choose action:\n1. Make an order\n2. Show my order\n3. Exit");
                int action = scanner.nextInt();

                if (action == 1) {
                    System.out.println("Enter a unique Order ID:");
                    int orderId = scanner.nextInt();

                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        // Insert order into orders table if it does not already exist
                        String insertOrderQuery = "INSERT INTO orders (order_id, order_details, total_amount) VALUES (?, '', 0) ON CONFLICT (order_id) DO NOTHING";
                        try (PreparedStatement stmt = connection.prepareStatement(insertOrderQuery)) {
                            stmt.setInt(1, orderId);
                            stmt.executeUpdate();
                        }

                        // Display menu items
                        String query = "SELECT * FROM menu_item";
                        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                            System.out.println("Menu:");
                            while (rs.next()) {
                                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - $" + rs.getDouble("price") + " (" + rs.getString("category") + ")");
                            }
                        }

                        // Add items to order
                        StringBuilder orderDetails = new StringBuilder();
                        double totalAmount = 0;
                        System.out.println("Enter the item ID to add to your order (0 to finish):");
                        while (true) {
                            int itemId = scanner.nextInt();
                            if (itemId == 0) break;

                            String getItemQuery = "SELECT name, price FROM menu_item WHERE id = ?";
                            try (PreparedStatement stmt = connection.prepareStatement(getItemQuery)) {
                                stmt.setInt(1, itemId);
                                try (ResultSet rs = stmt.executeQuery()) {
                                    if (rs.next()) {
                                        String itemName = rs.getString("name");
                                        double itemPrice = rs.getDouble("price");
                                        orderDetails.append(itemName).append(", ");
                                        totalAmount += itemPrice;
                                        System.out.println(itemName + " added to your order.");
                                    } else {
                                        System.out.println("Invalid item ID. Try again.");
                                    }
                                }
                            }
                        }

                        // Update order details and total amount
                        String updateOrderQuery = "UPDATE orders SET order_details = ?, total_amount = ? WHERE order_id = ?";
                        try (PreparedStatement stmt = connection.prepareStatement(updateOrderQuery)) {
                            stmt.setString(1, orderDetails.toString());
                            stmt.setDouble(2, totalAmount);
                            stmt.setInt(3, orderId);
                            stmt.executeUpdate();
                            System.out.println("Order updated successfully.");
                        }
                    } catch (SQLException e) {
                        System.err.println("Database error: " + e.getMessage());
                    }

                } else if (action == 2) {
                    System.out.println("Enter your Order ID:");
                    int orderId = scanner.nextInt();

                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        String query = "SELECT order_details, total_amount FROM orders WHERE order_id = ?";
                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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

                } else if (action == 3) {
                    continue;
                }

            } else if (role == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
