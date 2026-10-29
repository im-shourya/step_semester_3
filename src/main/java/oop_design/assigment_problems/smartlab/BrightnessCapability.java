package oop_design.assigment_problems.smartlab;

public class BrightnessCapability extends RangeCapability {

    public BrightnessCapability() {
        super(0, 100, 100, "%");
    }

    @Override
    public String getName() {
        return "Brightness";
    }
}
