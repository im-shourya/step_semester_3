package inheritance.assigment_problems;

import java.util.Arrays;

public class RaceDesk {

    // Most specific type first: every EliteRunnerEntry is also a RunnerEntry.
    public static String classifyGeneration(RaceEntry entry) {
        if (entry instanceof EliteRunnerEntry) {
            return "Multilevel descendant (3 generations deep)";
        } else if (entry instanceof RunnerEntry) {
            return "Direct descendant (2 generations deep)";
        } else if (entry instanceof RelayTeamEntry) {
            return "Hierarchical sibling (independent branch)";
        } else if (entry != null) {
            return "Base entry (1 generation)";
        }
        return "No entry";
    }

    public static double getTotalBalanceDue(RaceEntry[] entries) {
        double total = 0;
        for (RaceEntry entry : entries) {
            total += entry.getBalanceDue();
        }
        return total;
    }

    public static String announceAll(RaceEntry[] entries) {
        StringBuilder report = new StringBuilder();
        for (RaceEntry entry : entries) {
            report.append(entry.announce());
            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                report.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }

    /*
     * Single pass over the night's entries. Nulls are counted and skipped; relay teams and
     * individual entries (runners, elite runners, plain entries) are settled into separate totals.
     */
    public static String settleNight(RaceEntry[] entries) {
        if (entries == null) {
            return "0 processed | 0 null skipped | 0 relay | 0 individual";
        }

        int processed = 0;
        int nullSkipped = 0;
        int relays = 0;
        int individuals = 0;
        double relayBalance = 0;
        double individualBalance = 0;

        for (RaceEntry entry : entries) {
            if (entry == null) {
                nullSkipped++;
                continue;
            }
            if (entry instanceof RelayTeamEntry) {
                relays++;
                relayBalance += entry.getBalanceDue();
            } else {
                individuals++;
                individualBalance += entry.getBalanceDue();
            }
            processed++;
        }

        System.out.println("Outstanding - relay: " + relayBalance + ", individual: " + individualBalance);
        return processed + " processed | " + nullSkipped + " null skipped | "
                + relays + " relay | " + individuals + " individual";
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 2 ---");
        RunnerEntry runnerEntry = new RunnerEntry("BIB2001", 80, "Open 10K");
        EliteRunnerEntry eliteEntry = new EliteRunnerEntry("BIB3001", 150, "Elite Full Marathon", 500);
        RelayTeamEntry relayEntry = new RelayTeamEntry("BIB4001", 300, 4);

        System.out.println(runnerEntry.announce());
        System.out.println(eliteEntry.announce());
        System.out.println(relayEntry.announce());
        System.out.println(classifyGeneration(eliteEntry));
        System.out.println(classifyGeneration(relayEntry));
        System.out.println(getTotalBalanceDue(new RaceEntry[]{runnerEntry, eliteEntry, relayEntry}));

        System.out.println("--- Problem 3 ---");
        runnerEntry.pay(30);
        runnerEntry.applyLateFee(20);
        System.out.println(runnerEntry.getBalanceDue());
        double[] history = runnerEntry.getLateFeeHistory();
        System.out.println(Arrays.toString(history));
        history[0] = 999;
        System.out.println(Arrays.toString(runnerEntry.getLateFeeHistory()));

        System.out.println("--- Problem 4 ---");
        RaceEntry[] fleet = {runnerEntry, relayEntry};
        System.out.println("\"" + announceAll(fleet) + "\"");

        RaceEntry plain = new RaceEntry("BIB5001", 50);
        try {
            RelayTeamEntry bad = (RelayTeamEntry) plain;
            System.out.println(bad.getTeamSize());
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }

        System.out.println("--- Problem 5 ---");
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));

        runnerEntry.pay(10, "UPI");
        System.out.println(settleNight(new RaceEntry[]{eliteEntry, null, relayEntry}));

        try {
            new RelayTeamEntry("BIB6001", 200, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected relay: " + e.getMessage());
        }
        System.out.println(RaceEntry.getBibCounter());
        System.out.println("Entry codes: " + runnerEntry.getEntryCode() + ", " + eliteEntry.getEntryCode()
                + ", " + relayEntry.getEntryCode() + ", " + plain.getEntryCode());
    }
}
