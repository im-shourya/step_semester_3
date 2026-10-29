package oop_design.assigment_problems.smartlab;

// Shared range guard: the value is checked before the state changes, so a rejected command leaves the device untouched.
public abstract class RangeCapability implements Capability {

    private final int min;
    private final int max;
    private final String unit;
    private int value;

    protected RangeCapability(int min, int max, int initial, String unit) {
        this.min = min;
        this.max = max;
        this.unit = unit;
        this.value = initial;
    }

    @Override
    public String apply(int newValue) {
        if (newValue < min || newValue > max) {
            return getName().toLowerCase() + " must be between " + min + unit + " and " + max + unit;
        }
        value = newValue;
        return null;
    }

    @Override
    public String describe(String deviceName) {
        return deviceName + ": " + getName().toLowerCase() + " set to " + value + unit;
    }

    public int getValue() {
        return value;
    }
}
