package oop_design.assigment_problems.hackathon;

import java.util.List;

public class Team {

    private final String name;
    private final List<Student> members;
    private final ScoringRule track;
    private Project project;

    public Team(String name, List<Student> members, ScoringRule track) {
        this.name = name;
        this.members = List.copyOf(members);
        this.track = track;
    }

    public String getName() {
        return name;
    }

    public List<Student> getMembers() {
        return members;
    }

    public ScoringRule getTrack() {
        return track;
    }

    public Project getProject() {
        return project;
    }

    void setProject(Project project) {
        this.project = project;
    }
}
