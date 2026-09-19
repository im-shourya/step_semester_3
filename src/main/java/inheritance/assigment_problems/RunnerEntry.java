package inheritance.assigment_problems;

public class RunnerEntry extends RaceEntry {
    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    // Runner spots are limited, so late runners pay double the standard penalty.
    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category
                + " | Balance: " + getBalanceDue();
    }

    public String getCategory() {
        return category;
    }
}
