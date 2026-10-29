package oop_design.assigment_problems.elective;

public class Student {

    private final String name;
    private final CreditPolicy policy;
    private int credits;

    public Student(String name, CreditPolicy policy, int currentCredits) {
        this.name = name;
        this.policy = policy;
        this.credits = currentCredits;
    }

    public String getName() {
        return name;
    }

    public CreditPolicy getPolicy() {
        return policy;
    }

    public int getCredits() {
        return credits;
    }

    public boolean canTake(int extraCredits) {
        return credits + extraCredits <= policy.getCreditLimit();
    }

    void addCredits(int amount) {
        credits += amount;
    }

    public String creditSummary() {
        return credits + "/" + policy.getCreditLimit();
    }
}
