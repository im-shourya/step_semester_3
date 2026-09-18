package constructors.assigment_problems;

public class ReconciliationEngine {
    private int processed;
    private int nullSkipped;
    private int failed;
    private int premiumCount;
    private int regularCount;
    private double grandTotalSurge;

    /*
     * Premium members pay only half the surge fee; regular accounts pay it in full.
     * A null account is counted and skipped, so it never touches the grand total.
     */
    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account == null) {
            nullSkipped++;
            return;
        }

        try {
            account.setOrderValue(amount);
            double surgeFee = account.calculateSurgeFee(delayMinutes);

            if (account instanceof PremiumDeliveryAccount) {
                surgeFee *= (1 - PremiumDeliveryAccount.SURGE_DISCOUNT);
                premiumCount++;
            } else {
                regularCount++;
            }

            grandTotalSurge += surgeFee;
            processed++;
            System.out.printf("%s settled: surge fee Rs %.2f%n", account.getStudentId(), surgeFee);
        } catch (IllegalArgumentException e) {
            // A single bad entry (e.g. negative amount) is reported, not allowed to crash the run.
            failed++;
            System.out.println(account.getStudentId() + " failed: " + e.getMessage());
        }
    }

    /*
     * Mismatched array lengths reject the whole batch before anything runs. If the arrays
     * don't line up, there's no way to trust which amount belongs to which student, and
     * silently charging the wrong student is far worse than a run that refuses to start.
     */
    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            throw new IllegalArgumentException("Batch rejected: input arrays must not be null");
        }
        if (accounts.length != amounts.length || accounts.length != delayMinutesArray.length) {
            throw new IllegalArgumentException("Batch rejected: array lengths differ (accounts="
                    + accounts.length + ", amounts=" + amounts.length
                    + ", delays=" + delayMinutesArray.length + "); nothing was processed");
        }

        ReconciliationEngine engine = new ReconciliationEngine();
        for (int i = 0; i < accounts.length; i++) {
            engine.processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
        }
        engine.printSummary();
    }

    private void printSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append(processed).append(" processed | ")
                .append(nullSkipped).append(" null skipped | ")
                .append(premiumCount).append(" premium | ")
                .append(regularCount).append(" regular | ");
        if (failed > 0) {
            summary.append(failed).append(" failed | ");
        }
        summary.append(String.format("grand total surge fees = Rs %.2f", grandTotalSurge));
        System.out.println(summary);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
                new PremiumDeliveryAccount("STU001", 500),
                null,
                new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);

        System.out.println();
        try {
            processBatch(accounts, new double[]{500, 400}, delayMinutesArray);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
