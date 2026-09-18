package constructors.assigment_problems;

import java.util.Arrays;

public class Canteen implements Comparable<Canteen> {
    private static final int DEFAULT_TRUST_SCORE = 3;

    private final String canteenCode;
    private final String canteenName;
    private final int trustScore;

    public Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    public Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, DEFAULT_TRUST_SCORE);
    }

    /*
     * Ordering rule (negative = this canteen ranks first):
     * 1. Higher trustScore first.
     * 2. canteenCode alphabetically, ignoring case ("hb1-c" and "HB1-C" are the same canteen code
     *    for ranking purposes; the stored code keeps its original case for display).
     * 3. Shorter canteenName first.
     * 4. Final fallbacks (case-sensitive code, then name) so two distinct canteens never compare
     *    as equal, keeping the order identical on every refresh.
     */
    @Override
    public int compareTo(Canteen other) {
        int result = Integer.compare(other.trustScore, this.trustScore);
        if (result != 0) {
            return result;
        }
        result = this.canteenCode.compareToIgnoreCase(other.canteenCode);
        if (result != 0) {
            return result;
        }
        result = Integer.compare(this.canteenName.length(), other.canteenName.length());
        if (result != 0) {
            return result;
        }
        result = this.canteenCode.compareTo(other.canteenCode);
        if (result != 0) {
            return result;
        }
        return this.canteenName.compareTo(other.canteenName);
    }

    // Insertion sort on a copy, so the caller's array is left untouched.
    public static Canteen[] rankCanteens(Canteen[] canteens) {
        Canteen[] ranked = new Canteen[canteens.length];
        for (int i = 0; i < canteens.length; i++) {
            ranked[i] = canteens[i];
        }

        for (int i = 1; i < ranked.length; i++) {
            Canteen current = ranked[i];
            int j = i - 1;
            while (j >= 0 && ranked[j].compareTo(current) > 0) {
                ranked[j + 1] = ranked[j];
                j--;
            }
            ranked[j + 1] = current;
        }
        return ranked;
    }

    public String getCanteenCode() {
        return canteenCode;
    }

    @Override
    public String toString() {
        return canteenCode;
    }

    public static void main(String[] args) {
        Canteen[] canteens = {
                new Canteen("HB3-C", "Spice Junction", 3),
                new Canteen("hb1-c", "Grand Mess", 5),
                new Canteen("HB2-C", "Southern Treats")
        };

        System.out.println(Arrays.toString(rankCanteens(canteens)));
    }
}
