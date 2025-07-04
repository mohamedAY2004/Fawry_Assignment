import java.util.List;

public abstract class ShippingService {
    public static void ship(List<ShippableItem> items) {
        if(items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (ShippableItem item : items) {
            System.out.printf("%s      %.2fg%n", item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }
        System.out.printf("Total weight to ship %.2fg%n", totalWeight);
    }
}
