package constructors.assigment_problems;

public class DeliveryAccount {
    private static final double MINIMUM_SURGE_PERCENT;
    private static final double PROVISIONAL_ORDER_VALUE;
    private static final SurgeFeeCalculator SURGE_CALCULATOR;

    // One-time class-level setup: shared surge configuration used by every account.
    static {
        MINIMUM_SURGE_PERCENT = 1.0;
        PROVISIONAL_ORDER_VALUE = 0.0;
        SURGE_CALCULATOR = new SurgeFeeCalculator(MINIMUM_SURGE_PERCENT);
    }

    private final String studentId;
    private double orderValue;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    // Provisional account: order value is filled in when the account is settled.
    public DeliveryAccount(String studentId) {
        this(studentId, PROVISIONAL_ORDER_VALUE);
    }

    // Reuses Problem 4's tiered SurgeFeeCalculator (with a 1% minimum floor).
    public final double calculateSurgeFee(int delayMinutes) {
        return SURGE_CALCULATOR.calculateSurgeFee(orderValue, delayMinutes);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    void setOrderValue(double orderValue) {
        this.orderValue = orderValue;
    }
}
