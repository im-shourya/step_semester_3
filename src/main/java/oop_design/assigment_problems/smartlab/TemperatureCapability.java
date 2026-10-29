package oop_design.assigment_problems.smartlab;

public class TemperatureCapability extends RangeCapability {

    public TemperatureCapability() {
        super(16, 30, 24, "°C");
    }

    @Override
    public String getName() {
        return "Temperature";
    }
}
