package oop_design.assigment_problems.hackathon;

public class OpenScoring implements ScoringRule {

    @Override
    public String getTrackName() {
        return "Open";
    }

    @Override
    public double finalScore(Score s) {
        return (s.getIdea() + s.getExecution() + s.getPresentation()) / 3.0;
    }
}
