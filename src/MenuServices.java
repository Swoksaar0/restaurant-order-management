import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuServices {

    private Connection connection;

    public MenuServices(Connection connection) {
        this.connection = connection;
    }

    public void addMenuItem(String name, double price, String category) {
        String query = "INSERT INTO menu_item(name, price,category) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            preparedStatement.setString(3, category);

            int rowsInserted = preparedStatement.executeUpdate();
            System.out.println("Menu item added successfully. Rows affected: " + rowsInserted);
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
        }
    }
    public class MenuItemDeleter {
        public static void deleteMenuItem(Connection connection, int id) {
            String query = "DELETE FROM menu_item WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);

                int rowsDeleted = preparedStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Menu item deleted successfully.");
                } else {
                    System.out.println("No item found with the given ID.");
                }
            } catch (SQLException e) {
                System.err.println("Error deleting menu item: " + e.getMessage());
            }
        }
    }
}
