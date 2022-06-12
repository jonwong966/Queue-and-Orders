import java.util.*;

public class OrderQueue extends AutoDrinkCheck {
    // public InventoryCheck inven = new InventoryCheck();
    // public Hashtable<String, String> order = new Hashtable<String, String>();
    // public String drink;
    // public String name;
    // public Queue<Hashtable> queue = new LinkedList<Hashtable>();

    public static void main(String[] args) {
        AutoDrinkCheck check = new AutoDrinkCheck();
        check.AvailableDrinkType();

        Hashtable<String, String> order = new Hashtable<String, String>();
        String name;
        String drink;
        Queue<Hashtable> queue = new LinkedList<Hashtable>();
        Scanner scan = new Scanner(System.in);

        System.out.println("Input your name: ");
        name = scan.nextLine();
        System.out.println("Input your order: ");
        drink = scan.nextLine();
        System.out.println();

        System.out.println("Sending to queue");
        order.put(name, drink);
        queue.add(order);
        System.out.println();
        System.out.println("Current queue: " + queue + check.drink.get(drink));// .drink.get(drink));
        System.out.println();

        queue.remove();
        System.out.println("Current queue: " + queue);

    }

}
