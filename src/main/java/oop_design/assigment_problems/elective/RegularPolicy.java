package oop_design.assigment_problems.elective;

public class RegularPolicy implements CreditPolicy {

    @Override
    public String getTypeName() {
        return "Regular";
    }

    @Override
    public int getCreditLimit() {
        return 24;
    }
}
