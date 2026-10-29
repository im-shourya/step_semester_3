package oop_design.assigment_problems.hackathon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Hackathon 1 --- 0..* Team, Team 1 --- 2..4 Student, Team 1 --- 0..1 Project,
 * Project 1 --- 0..1 Score, Team * --- 1 ScoringRule (track).
 * Flow: register() checks size and one-team-per-student, submit() attaches one Project,
 * score() moves the hackathon to JUDGING and stores a Score, publish() locks everything.
 * The final score is asked from the team's ScoringRule, so a new track is just a new class.
 */
public class Hackathon {

    public enum State { OPEN, JUDGING, PUBLISHED }

    private final String name;
    private final List<Team> teams = new ArrayList<>();
    private final Set<Student> registeredStudents = new HashSet<>();
    private State state = State.OPEN;

    public Hackathon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public Team register(String teamName, List<Student> members, ScoringRule track) {
        if (state != State.OPEN) {
            System.out.println("Registration failed: Registration is closed.");
            return null;
        }
        if (members.size() < 2 || members.size() > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members.");
            return null;
        }
        for (Student s : members) {
            if (registeredStudents.contains(s)) {
                System.out.println("Registration failed: " + s.getName() + " is already in a team.");
                return null;
            }
        }
        Team team = new Team(teamName, members, track);
        teams.add(team);
        registeredStudents.addAll(members);
        System.out.println("Team " + teamName + " registered (" + members.size() + " members, "
                + track.getTrackName() + " track).");
        return team;
    }

    public Project submit(Team team, String title) {
        if (state != State.OPEN) {
            System.out.println("Submission failed: Submissions are closed.");
            return null;
        }
        if (team.getProject() != null) {
            System.out.println("Submission failed: " + team.getName() + " has already submitted a project.");
            return null;
        }
        Project project = new Project(title, team);
        team.setProject(project);
        System.out.println("Project '" + title + "' submitted by " + team.getName() + ".");
        return project;
    }

    public boolean score(Judge judge, Project project, int idea, int execution, int presentation) {
        if (state == State.PUBLISHED) {
            System.out.println("Rescore rejected: Results have already been published.");
            return false;
        }
        state = State.JUDGING;
        project.setScore(new Score(idea, execution, presentation));
        System.out.println("Score recorded for '" + project.getTitle() + "'.");
        return true;
    }

    public double finalScore(Project project) {
        return project.getTeam().getTrack().finalScore(project.getScore());
    }

    public void publish() {
        state = State.PUBLISHED;
        System.out.println("Results published.");
    }
}
