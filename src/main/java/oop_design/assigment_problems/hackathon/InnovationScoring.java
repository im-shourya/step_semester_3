package oop_design.assigment_problems.hackathon;

public class InnovationScoring implements ScoringRule {

    @Override
    public String getTrackName() {
        return "Innovation";
    }

    @Override
    public double finalScore(Score s) {
        return s.getIdea() * 0.5 + s.getExecution() * 0.3 + s.getPresentation() * 0.2;
    }
}
