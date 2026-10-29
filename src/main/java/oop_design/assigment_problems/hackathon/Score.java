package oop_design.assigment_problems.hackathon;

public class Score {

    private final int idea;
    private final int execution;
    private final int presentation;

    public Score(int idea, int execution, int presentation) {
        if (!inRange(idea) || !inRange(execution) || !inRange(presentation)) {
            throw new IllegalArgumentException("Each rating must be between 0 and 10");
        }
        this.idea = idea;
        this.execution = execution;
        this.presentation = presentation;
    }

    private static boolean inRange(int rating) {
        return rating >= 0 && rating <= 10;
    }

    public int getIdea() {
        return idea;
    }

    public int getExecution() {
        return execution;
    }

    public int getPresentation() {
        return presentation;
    }
}
