package oop_design.assigment_problems.hackathon;

import java.util.List;

public class HackathonDemo {

    public static void main(String[] args) {
        Hackathon codeSprint = new Hackathon("Code Sprint");
        Judge judge = new Judge("Dr. Rao");

        Team byteBusters = codeSprint.register("ByteBusters",
                List.of(new Student("Asha"), new Student("Ravi"), new Student("Neha")), new InnovationScoring());
        codeSprint.register("SoloCoder", List.of(new Student("Kiran")), new OpenScoring());

        Project smartAttend = codeSprint.submit(byteBusters, "SmartAttend");
        codeSprint.score(judge, smartAttend, 8, 7, 9);
        System.out.printf("Final score: %.2f.%n", codeSprint.finalScore(smartAttend));

        codeSprint.publish();
        codeSprint.score(judge, smartAttend, 10, 7, 9);
    }
}
