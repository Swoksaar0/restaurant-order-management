import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        String dbUrl = "jdbc:postgresql://localhost:2020/postgres";
        String username = "postgres";
        String password = "dima2345";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose role");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Quit");
            int role = scanner.nextInt();
            if (role == 1) {
                System.out.println("Choose action\n1.Add new menu item\n2.Delete menu item\n3.Exit");
                int action = scanner.nextInt();
                if (action == 1) {
                    System.out.println("Name:");
                    String name = scanner.next();
                    System.out.println("price:");
                    double price = scanner.nextDouble();
                    System.out.println("Category:");
                    String category = scanner.next();
                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        MenuServices menuServices = new MenuServices(connection);
                        menuServices.addMenuItem(name, price, category);//add item to menu

                    } catch (Exception e) {
                        System.err.println("Database connection error: " + e.getMessage());
                    }
                } else if (action == 2) {
                    System.out.println("Item ID:");
                    int itemID = scanner.nextInt();
                    try (Connection connection = DriverManager.getConnection(dbUrl, username, password)) {
                        int idToDelete = itemID;
                        MenuServices.MenuItemDeleter.deleteMenuItem(connection, idToDelete);
                    } catch (SQLException e) {
                        System.err.println("Database connection error: " + e.getMessage());
                    }
                }
            }else if(role==2){
                int action;
                while(true){
                    System.out.println("Choose action\n1.Make an order\n2.Show my order\n3.Exit");
                    action = scanner.nextInt();
                    if(action==1){
                    }

                }

            }else if(role==3){
                break;
            }
        }
    }
}