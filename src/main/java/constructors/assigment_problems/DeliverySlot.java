package constructors.assigment_problems;

public class DeliverySlot {
    private static final String DEFAULT_SLOT = "ASAP";
    private static final String[] PEAK_SLOTS = {"12:00-13:00", "13:00-14:00", "19:00-20:00", "20:00-21:00"};

    private final String orderId;
    private final String timeSlot;

    public DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    public DeliverySlot(String orderId) {
        this(orderId, DEFAULT_SLOT);
    }

    public boolean isPeakHour() {
        for (String peak : PEAK_SLOTS) {
            if (peak.equals(timeSlot)) {
                return true;
            }
        }
        return false;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public static void main(String[] args) {
        DeliverySlot[] slots = {
                new DeliverySlot("ORD101", "13:00-14:00"),
                new DeliverySlot("ORD102"),
                new DeliverySlot("ORD103", "16:00-17:00"),
                new DeliverySlot("ORD104", "20:00-21:00")
        };

        for (DeliverySlot slot : slots) {
            System.out.println(slot.getOrderId() + " [" + slot.getTimeSlot() + "] peak: " + slot.isPeakHour());
        }
    }
}
