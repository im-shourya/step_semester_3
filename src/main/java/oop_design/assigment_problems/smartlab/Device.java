package oop_design.assigment_problems.smartlab;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Device 1 --- 0..* Capability (composition). A new capability is a new Capability class,
 * not a new Device subclass, so capability combinations never multiply the class count.
 */
public class Device {

    private final String name;
    private final Map<String, Capability> capabilities = new LinkedHashMap<>();

    public Device(String name, Capability... initial) {
        this.name = name;
        for (Capability c : initial) {
            capabilities.put(c.getName(), c);
        }
    }

    public String getName() {
        return name;
    }

    public boolean has(String capabilityName) {
        return capabilities.containsKey(capabilityName);
    }

    public void addCapability(Capability capability) {
        capabilities.put(capability.getName(), capability);
        System.out.println(name + ": " + capability.getName() + " capability added.");
    }

    public boolean set(String capabilityName, int value) {
        Capability capability = capabilities.get(capabilityName);
        if (capability == null) {
            System.out.println("Rejected: " + name + " has no " + capabilityName + " capability");
            return false;
        }
        String error = capability.apply(value);
        if (error != null) {
            System.out.println("Rejected: " + name + " " + error + ".");
            return false;
        }
        System.out.println(capability.describe(name) + ".");
        return true;
    }
}
