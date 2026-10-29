package oop_design.assigment_problems.smartcard;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/*
 * SmartCard 1 --- 0..* Transaction (composition), SmartCard * --- 1 PricingPlan.
 * The balance is derived from the transaction list, so 'balance = sum of transactions'
 * holds by construction; every operation validates first and only then appends one transaction.
 */
public class SmartCard {

    private static final double MIN_TOP_UP = 100;
    private static final double MAX_BALANCE = 5000;

    private final String cardId;
    private final PricingPlan plan;
    private final List<Transaction> transactions = new ArrayList<>();
    private boolean blocked;

    public SmartCard(String cardId, PricingPlan plan) {
        this.cardId = cardId;
        this.plan = plan;
    }

    public double getBalance() {
        double sum = 0;
        for (Transaction t : transactions) {
            sum += t.getAmount();
        }
        return Math.round(sum * 100) / 100.0;
    }

    public boolean topUp(double amount) {
        if (blocked) {
            System.out.println("Top-up failed: " + cardId + " is blocked.");
            return false;
        }
        if (amount < MIN_TOP_UP) {
            System.out.printf("Top-up failed: Minimum top-up is ₹%.2f.%n", MIN_TOP_UP);
            return false;
        }
        if (getBalance() + amount > MAX_BALANCE) {
            System.out.printf("Top-up failed: Balance cannot exceed ₹%.2f.%n", MAX_BALANCE);
            return false;
        }
        transactions.add(new Transaction(Transaction.Type.TOP_UP, "Top-up", amount));
        System.out.printf("%s topped up with ₹%.2f. Balance: ₹%.2f.%n", cardId, amount, getBalance());
        return true;
    }

    public Transaction purchase(String item, double listPrice) {
        if (blocked) {
            System.out.println("Purchase failed: " + cardId + " is blocked.");
            return null;
        }
        double price = plan.priceFor(listPrice);
        if (price > getBalance()) {
            System.out.printf("Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                    price, getBalance());
            return null;
        }
        Transaction t = new Transaction(Transaction.Type.PURCHASE, item, -price);
        transactions.add(t);
        System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f.%n", item, price, getBalance());
        return t;
    }

    public boolean refund(Transaction purchase) {
        if (purchase == null || purchase.getType() != Transaction.Type.PURCHASE || !transactions.contains(purchase)) {
            System.out.println("Refund rejected: Not a purchase on " + cardId + ".");
            return false;
        }
        if (purchase.isRefunded()) {
            System.out.println("Refund rejected: " + purchase.getDescription() + " has already been refunded.");
            return false;
        }
        double amount = -purchase.getAmount();
        purchase.markRefunded();
        transactions.add(new Transaction(Transaction.Type.REFUND, "Refund " + purchase.getDescription(), amount));
        System.out.printf("Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                amount, purchase.getDescription(), getBalance());
        return true;
    }

    public void block() {
        blocked = true;
        System.out.println(cardId + " blocked.");
    }

    public void unblock() {
        blocked = false;
        System.out.println(cardId + " unblocked.");
    }

    public String miniStatement() {
        StringJoiner lines = new StringJoiner(", ");
        for (Transaction t : transactions) {
            lines.add(String.format("%+.2f", t.getAmount()));
        }
        return String.format("Mini-statement for %s: %s = ₹%.2f.", cardId, lines, getBalance());
    }
}
