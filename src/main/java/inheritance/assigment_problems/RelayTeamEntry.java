package inheritance.assigment_problems;

// Hierarchical inheritance: a sibling of RunnerEntry, extending RaceEntry directly.
public class RelayTeamEntry extends RaceEntry {
    private final int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        // teamSize is checked inside the super(...) call, so an invalid team is rejected
        // before RaceEntry's constructor runs and uses up an entry code.
        super(requireValidTeam(bibNumber, teamSize), entryFee);
        this.teamSize = teamSize;
    }

    private static String requireValidTeam(String bibNumber, int teamSize) {
        if (teamSize <= 0) {
            throw new IllegalArgumentException("teamSize must be positive: " + teamSize);
        }
        return bibNumber;
    }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize
                + " | Balance: " + getBalanceDue();
    }

    public int getTeamSize() {
        return teamSize;
    }
}
