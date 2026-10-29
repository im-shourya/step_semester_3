package oop_design.assigment_problems.smartcard;

public class Transaction {

    public enum Type { TOP_UP, PURCHASE, REFUND }

    private final Type type;
    private final String description;
    private final double amount;
    private boolean refunded;

    Transaction(Type type, String description, double amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    // Signed: credits are positive, purchases negative.
    public double getAmount() {
        return amount;
    }

    public boolean isRefunded() {
        return refunded;
    }

    void markRefunded() {
        refunded = true;
    }
}
