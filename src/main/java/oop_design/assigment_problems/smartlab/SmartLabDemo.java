package oop_design.assigment_problems.smartlab;

import java.util.List;

public class SmartLabDemo {

    public static void main(String[] args) {
        Device labAc = new Device("Lab AC", new PowerCapability(), new TemperatureCapability());
        Device lights = new Device("Ceiling Lights", new PowerCapability(), new BrightnessCapability());
        Device projector = new Device("Projector", new PowerCapability());

        new Scene("Lecture Mode")
                .addStep("Power", 1)
                .addStep("Brightness", 40)
                .addStep("Temperature", 24)
                .run(List.of(labAc, lights, projector));

        labAc.set("Temperature", 12);

        projector.addCapability(new BrightnessCapability());
        projector.set("Brightness", 70);
    }
}
