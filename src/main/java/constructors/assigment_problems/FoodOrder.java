package constructors.assigment_problems;

public class FoodOrder {
    private final String studentName;
    private final String dishName;
    private boolean delivered;

    /*
     * No no-argument constructor is declared. Because a parameterized constructor exists,
     * Java does not generate a default one, so every FoodOrder must go through validation.
     */
    public FoodOrder(String studentName, String dishName) {
        if (isBlank(studentName)) {
            throw new IllegalArgumentException("studentName is missing or blank");
        }
        if (isBlank(dishName)) {
            throw new IllegalArgumentException("dishName is missing or blank");
        }
        this.studentName = studentName.trim();
        this.dishName = dishName.trim();
        this.delivered = false;
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public void markDelivered() {
        if (delivered) {
            System.out.println("WARNING: " + dishName + " for " + studentName
                    + " was already delivered - possible double-serve!");
            return;
        }
        delivered = true;
        System.out.println("Delivered: " + dishName + " for " + studentName);
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {
            String[] raw = rawOrders[i];
            try {
                if (raw == null || raw.length < 2) {
                    throw new IllegalArgumentException("entry is incomplete");
                }
                new FoodOrder(raw[0], raw[1]);
                valid++;
            } catch (IllegalArgumentException e) {
                rejected++;
                System.out.println("Rejected entry " + i + ": " + e.getMessage());
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] rawOrders = {
                {"Ravi", "Paneer Butter Masala"},
                {"", "Chole Bhature"},
                {"Meera", " "},
                {"Divya", "Veg Biryani"}
        };
        processBatch(rawOrders);

        System.out.println();
        FoodOrder order = new FoodOrder("Ravi", "Paneer Butter Masala");
        order.markDelivered();
        order.markDelivered();
    }
}
