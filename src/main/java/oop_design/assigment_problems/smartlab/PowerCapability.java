package oop_design.assigment_problems.smartlab;

public class PowerCapability implements Capability {

    private boolean on;

    @Override
    public String getName() {
        return "Power";
    }

    // 1 = ON, 0 = OFF
    @Override
    public String apply(int value) {
        if (value != 0 && value != 1) {
            return "power must be ON (1) or OFF (0)";
        }
        on = value == 1;
        return null;
    }

    @Override
    public String describe(String deviceName) {
        return deviceName + ": " + (on ? "ON" : "OFF");
    }
}
