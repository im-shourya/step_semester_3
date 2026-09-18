package constructors.assigment_problems;

public class PremiumDeliveryAccount extends DeliveryAccount {
    public static final double SURGE_DISCOUNT = 0.5;

    public PremiumDeliveryAccount(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public PremiumDeliveryAccount(String studentId) {
        super(studentId);
    }
}
