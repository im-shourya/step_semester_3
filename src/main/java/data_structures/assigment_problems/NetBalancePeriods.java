package data_structures.assigment_problems;

import java.util.HashMap;
import java.util.Map;

/*
 * Brute force: O(n^2) with a running sum (O(n^3) if each subarray is re-summed).
 * Prefix sums + hash map: O(n) time, O(n) extra space.
 */
public class NetBalancePeriods {

    public static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> seen = new HashMap<>();
        seen.put(0L, 1); // empty prefix, so periods starting at day 0 are counted
        long running = 0;
        long count = 0;
        for (int t : transactions) {
            running += t;
            count += seen.getOrDefault(running - k, 0);
            seen.merge(running, 1, Integer::sum);
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPeriods(new int[]{3, 4, -7, 1, 3, 3, 1, -4}, 7));
        System.out.println(countPeriods(new int[]{1, 2, 3}, 10));
    }
}
