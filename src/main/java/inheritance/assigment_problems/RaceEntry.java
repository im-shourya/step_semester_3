package inheritance.assigment_problems;

import java.util.Arrays;

public class RaceEntry {
    private static final int MIN_BIB_LENGTH = 4;
    private static int bibCounter = 0;

    private final String entryCode;
    private final String bibNumber;
    private final double entryFee;
    private double amountPaid;
    private double lateFeesTotal;
    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount;

    public RaceEntry(String bibNumber, double entryFee) {
        if (bibNumber == null || bibNumber.trim().length() < MIN_BIB_LENGTH) {
            throw new IllegalArgumentException("bibNumber must have at least "
                    + MIN_BIB_LENGTH + " non-blank characters: \"" + bibNumber + "\"");
        }
        if (entryFee <= 0) {
            throw new IllegalArgumentException("entryFee must be positive: " + entryFee);
        }
        this.bibNumber = bibNumber.trim();
        this.entryFee = entryFee;

        // Only reached once validation passes, so a rejected entry never uses up a number.
        bibCounter++;
        this.entryCode = "ENT-" + bibCounter;
    }

    public void pay(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("payment must be positive: " + amount);
        }
        amountPaid += amount;
    }

    // Records the mode, then reuses the flat pay(...) for the actual payment logic.
    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    // The single place a penalty is added and recorded; overrides route through here via super.
    protected void applyLateFee(double amount) {
        lateFeesTotal += amount;
        if (lateFeeCount == lateFeeHistory.length) {
            lateFeeHistory = Arrays.copyOf(lateFeeHistory, lateFeeHistory.length * 2);
        }
        lateFeeHistory[lateFeeCount++] = amount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }

    public double getBalanceDue() {
        return entryFee - amountPaid + lateFeesTotal;
    }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + getBalanceDue();
    }

    public String getEntryCode() {
        return entryCode;
    }

    public String getBibNumber() {
        return bibNumber;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    // "M" + three digits + one uppercase letter, e.g. "M123A".
    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) {
            return false;
        }
        if (code.charAt(0) != 'M') {
            return false;
        }
        for (int i = 1; i <= 3; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }
        return Character.isUpperCase(code.charAt(4));
    }

    // Validation lives only in the constructor; this just tries each one and counts failures.
    public static String registerBatch(String[] bibNumbers, double entryFee) {
        int registered = 0;
        int rejected = 0;
        for (String bib : bibNumbers) {
            try {
                new RaceEntry(bib, entryFee);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        try {
            new RaceEntry("B1", 50);
        } catch (IllegalArgumentException e) {
            System.out.println("new RaceEntry(\"B1\", 50) -> construction rejected");
        }

        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        System.out.println(r.getBalanceDue());

        System.out.println(registerBatch(new String[]{"BIB1", "B1", "BIB2"}, 80));
    }
}
