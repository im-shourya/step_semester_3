package oop_design.assigment_problems.elective;

public class HonorsPolicy implements CreditPolicy {

    @Override
    public String getTypeName() {
        return "Honors";
    }

    @Override
    public int getCreditLimit() {
        return 28;
    }
}
