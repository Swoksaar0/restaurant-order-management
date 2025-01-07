import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a restaurant and add menu items
        Restaurant restaurant = new Restaurant("My Fancy Restaurant");
        restaurant.addMenuItem(new MenuItem("Pizza", 8.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem("Burger", 5.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem("Cola", 1.99, "Drink"));
        restaurant.addMenuItem(new MenuItem("Water", 0.99, "Drink"));
        restaurant.addMenuItem(new MenuItem("Salad", 4.99, "Appetizer"));

        Order currentOrder = null; // To hold the current order

        while (true) {
            // Display menu options
            System.out.println("\nWelcome to " + restaurant.getName() + "!");
            System.out.println("Choose an action:");
            System.out.println("1. Show all menu items");
            System.out.println("2. Filter menu by category");
            System.out.println("3. Search menu item by name");
            System.out.println("4. Make an order");
            System.out.println("5. Show current order details");
            System.out.println("6. Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            if (choice == 1) {
                // Show all menu items
                System.out.println("\nRestaurant menu:");
                for (MenuItem item : restaurant.getMenu()) {
                    System.out.println(item);
                }
            } else if (choice == 2) {
                // Filter menu by category
                System.out.print("\nEnter a category (e.g., Drink, Main Course): ");
                String category = scanner.nextLine();
                List<MenuItem> filteredMenu = restaurant.filterMenuByCategory(category);

                if (!filteredMenu.isEmpty()) {
                    System.out.println("\nItems in category \"" + category + "\":");
                    for (MenuItem item : filteredMenu) {
                        System.out.println(item);
                    }
                } else {
                    System.out.println("\nNo items found in category \"" + category + "\".");
                }
            } else if (choice == 3) {
                // Search menu item by name
                System.out.print("\nEnter the name of the item: ");
                String name = scanner.nextLine();
                MenuItem foundItem = restaurant.searchMenuItemByName(name);

                if (foundItem != null) {
                    System.out.println("\nItem found:");
                    System.out.println(foundItem);
                } else {
                    System.out.println("\nItem \"" + name + "\" not found.");
                }
            } else if (choice == 4) {
                // Make an order
                if (currentOrder == null) {
                    System.out.print("\nEnter a unique Order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    currentOrder = restaurant.createOrder(orderId);
                    System.out.println("Order created with ID: " + orderId);
                }

                System.out.println("\nAdd items to your order. Enter the number corresponding to the menu item. Enter 0 to finish.");
                for (int i = 0; i < restaurant.getMenu().size(); i++) {
                    System.out.println((i + 1) + ". " + restaurant.getMenu().get(i));
                }

                while (true) {
                    System.out.print("Enter item number: ");
                    int itemChoice = scanner.nextInt();

                    if (itemChoice == 0) {
                        break; // Exit the loop when user is done adding items
                    }

                    if (itemChoice > 0 && itemChoice <= restaurant.getMenu().size()) {
                        MenuItem selectedItem = restaurant.getMenu().get(itemChoice - 1);
                        currentOrder.addItem(selectedItem);
                        System.out.println(selectedItem.getName() + " added to your order.");
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 5) {
                // Show current order details
                if (currentOrder != null) {
                    System.out.println("\nCurrent order details:");
                    currentOrder.showOrderDetails();
                } else {
                    System.out.println("\nNo order created yet.");
                }
            } else if (choice == 6) {
                // Exit
                System.out.println("Thank you for using the program!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

