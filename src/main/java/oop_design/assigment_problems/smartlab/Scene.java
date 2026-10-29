package oop_design.assigment_problems.smartlab;

import java.util.ArrayList;
import java.util.List;

/*
 * Scene 1 --- 1..* SceneStep (ordered). Each step is offered to every device; a device
 * without the step's capability is skipped, so the scene never needs to know device types.
 */
public class Scene {

    private final String name;
    private final List<SceneStep> steps = new ArrayList<>();

    public Scene(String name) {
        this.name = name;
    }

    public Scene addStep(String capabilityName, int value) {
        steps.add(new SceneStep(capabilityName, value));
        return this;
    }

    public int run(List<Device> devices) {
        System.out.println("Scene '" + name + "' started.");
        int applied = 0;
        for (SceneStep step : steps) {
            for (Device device : devices) {
                if (device.has(step.getCapabilityName()) && device.set(step.getCapabilityName(), step.getValue())) {
                    applied++;
                }
            }
        }
        System.out.println("Scene '" + name + "' completed: " + applied + " actions applied.");
        return applied;
    }
}
