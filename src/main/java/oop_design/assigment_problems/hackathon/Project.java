package oop_design.assigment_problems.hackathon;

public class Project {

    private final String title;
    private final Team team;
    private Score score;

    Project(String title, Team team) {
        this.title = title;
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public Team getTeam() {
        return team;
    }

    public Score getScore() {
        return score;
    }

    void setScore(Score score) {
        this.score = score;
    }
}
