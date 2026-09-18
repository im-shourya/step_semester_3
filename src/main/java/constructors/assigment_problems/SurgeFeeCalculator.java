package constructors.assigment_problems;

/*
 * - The class is final so no subclass can change how surge fees are calculated.
 * - minimumSurgePercent is final so the configured floor cannot change after construction.
 * - calculateSurgeFee is final to lock the calculation rule itself (as required by the signature).
 */
public final class SurgeFeeCalculator {
    private static final int TIER_1_END = 5;
    private static final int TIER_2_END = 15;
    private static final double TIER_1_RATE = 0.005;
    private static final double TIER_2_RATE = 0.01;
    private static final double TIER_3_RATE = 0.02;

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        if (minimumSurgePercent < 0) {
            throw new IllegalArgumentException("minimumSurgePercent cannot be negative");
        }
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0) {
            throw new IllegalArgumentException("orderValue cannot be negative");
        }
        if (delayMinutes < 0) {
            throw new IllegalArgumentException("delayMinutes cannot be negative");
        }
        if (delayMinutes == 0) {
            return 0.0;
        }

        // Minutes that fall inside each bracket; each bracket only charges its own minutes.
        int tier1Minutes = Math.min(delayMinutes, TIER_1_END);
        int tier2Minutes = Math.max(0, Math.min(delayMinutes, TIER_2_END) - TIER_1_END);
        int tier3Minutes = Math.max(0, delayMinutes - TIER_2_END);

        double tieredFee = orderValue * (tier1Minutes * TIER_1_RATE
                + tier2Minutes * TIER_2_RATE
                + tier3Minutes * TIER_3_RATE);
        double floorFee = orderValue * minimumSurgePercent / 100.0;

        return Math.max(tieredFee, floorFee);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calculator = new SurgeFeeCalculator(1.0);
        int[] delays = {0, 1, 6, 15, 16};

        for (int delay : delays) {
            System.out.println("orderValue = 500, delayMinutes = " + delay
                    + " -> Rs " + calculator.calculateSurgeFee(500, delay));
        }

        try {
            calculator.calculateSurgeFee(500, -3);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
    }
}
