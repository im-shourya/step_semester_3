package oop_design.assigment_problems.elective;

public class ExchangePolicy implements CreditPolicy {

    @Override
    public String getTypeName() {
        return "Exchange";
    }

    @Override
    public int getCreditLimit() {
        return 20;
    }
}
