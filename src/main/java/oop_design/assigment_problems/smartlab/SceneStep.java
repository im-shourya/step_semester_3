package oop_design.assigment_problems.smartlab;

public class SceneStep {

    private final String capabilityName;
    private final int value;

    public SceneStep(String capabilityName, int value) {
        this.capabilityName = capabilityName;
        this.value = value;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public int getValue() {
        return value;
    }
}
