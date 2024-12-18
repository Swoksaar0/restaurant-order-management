public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant("My Fancy Restaurant");

        MenuItem pizza = new MenuItem("Pizza", 8.99, "Main Course");
        MenuItem burger = new MenuItem("Burger", 5.99, "Main Course");
        MenuItem cola = new MenuItem("Cola", 1.99, "Drink");

        restaurant.addMenuItem(pizza);
        restaurant.addMenuItem(burger);
        restaurant.addMenuItem(cola);

        Order order1 = restaurant.createOrder(1);
        order1.addItem(pizza);
        order1.addItem(cola);

        Order order2 = restaurant.createOrder(2);
        order2.addItem(burger);
        order2.addItem(cola);

        System.out.println(restaurant);
        System.out.println("Order 1 Details: " + order1);
        System.out.println("Order 2 Details: " + order2);
    }
}


