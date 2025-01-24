import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant("My Fancy Restaurant");

        // Add menu items
        restaurant.addMenuItem(new MenuItem("Pizza", 8.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem("Burger", 5.99, "Main Course"));
        restaurant.addMenuItem(new MenuItem("Cola", 1.99, "Drink"));
        restaurant.addMenuItem(new MenuItem("Salad", 4.99, "Appetizer"));

        System.out.println("Welcome to " + restaurant.getName() + "!");
        System.out.println("Here is our menu:");
        int index = 1;
        for (MenuItem item : restaurant.getMenu()) {
            System.out.println(index++ + ". " + item);
        }

        System.out.print(
                "Please enter your Order ID: ");
        int orderId = scanner.nextInt();
        Order clientOrder = restaurant.createOrder(orderId);

        System.out.println("Add items to your order by entering their numbers. Enter 0 to finish.");
        while (true) {
            System.out.print("Item number: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                break;
            }

            if (choice > 0 && choice <= restaurant.getMenu().size()) {
                MenuItem selectedItem = restaurant.getMenu().get(choice - 1);
                clientOrder.addItem(selectedItem);
                System.out.println(selectedItem.getName() + " added to your order.");
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println(
                "Your order details:");
        System.out.println(clientOrder);

        System.out.println(
                "All orders:");
        for (Order order : restaurant.getOrders()) {
            System.out.println(order);
        }

        scanner.close();
    }
}

