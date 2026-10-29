package oop_design.assigment_problems.hackathon;

// Each track plugs in its own rule; Hackathon never needs to know which one it holds.
public interface ScoringRule {
    String getTrackName();

    double finalScore(Score score);
}
