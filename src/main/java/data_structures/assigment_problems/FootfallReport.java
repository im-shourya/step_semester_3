package data_structures.assigment_problems;

import java.util.ArrayList;
import java.util.List;

/*
 * Naive: O(n) per query, O(n * q) for all queries.
 * Optimized: O(n) one-time prefix build + O(1) per query = O(n + q) time, O(n) extra space.
 * The preprocessing pays off as soon as there is more than a handful of queries on the same day.
 */
public class FootfallReport {

    public static List<Long> footfallReport(int[] visitors, int[][] queries) {
        // prefix[i] = sum of visitors[0..i-1]; the extra leading 0 removes the start == 0 special case.
        long[] prefix = new long[visitors.length + 1];
        for (int i = 0; i < visitors.length; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }
        List<Long> totals = new ArrayList<>();
        for (int[] q : queries) {
            totals.add(prefix[q[1] + 1] - prefix[q[0]]);
        }
        return totals;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};
        System.out.println(footfallReport(visitors, queries));
    }
}
