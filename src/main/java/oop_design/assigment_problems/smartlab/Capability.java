package oop_design.assigment_problems.smartlab;

public interface Capability {
    String getName();

    // Returns an error message when the value is invalid, or null after applying it.
    String apply(int value);

    String describe(String deviceName);
}
