import java.util.Scanner;

public class UserInterface {
    private final menumanager menuManager = new menumanager();
    private final OrderManager orderManager = new OrderManager();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("Choose role:\n1. Admin\n2. User\n3. Quit");
            int role = scanner.nextInt();

            if (role == 1) {
                handleAdmin();
            } else if (role == 2) {
                handleUser();
            } else {
                break;
            }
        }
        scanner.close();
    }

    private void handleAdmin() {
        System.out.println("Choose action:\n1. Add menu item\n2. Delete menu item\n3. Exit");
        int action = scanner.nextInt();

        if (action == 1) {
            System.out.println("Name:");
            String name = scanner.next();
            System.out.println("Price:");
            double price = scanner.nextDouble();
            System.out.println("Category:");
            String category = scanner.next();
            menuManager.addMenuItem(name, price, category);
        } else if (action == 2) {
            System.out.println("Item ID to delete:");
            int itemId = scanner.nextInt();
            menuManager.deleteMenuItem(itemId);
        }
    }

    private void handleUser() {
        System.out.println("Choose action:\n1. Make an order\n2. Show my order\n3. Exit");
        int action = scanner.nextInt();

        if (action == 1) {
            System.out.println("Enter a unique Order ID:");
            int orderId = scanner.nextInt();
            orderManager.createOrder(orderId);
        } else if (action == 2) {
            System.out.println("Enter your Order ID:");
            int orderId = scanner.nextInt();
            orderManager.displayOrder(orderId);
        }
    }

    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
