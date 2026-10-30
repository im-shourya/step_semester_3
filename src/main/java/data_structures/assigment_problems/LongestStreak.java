package data_structures.assigment_problems;

import java.util.Arrays;

/*
 * Checking every run: O(n^2) (O(n^3) if each sum is recomputed).
 * Sliding window: O(n) time, O(1) space; each index enters and leaves the window once.
 * Non-negative costs mean adding a day never lowers the sum and removing one never raises it,
 * so once the window is over budget, only moving the left end can fix it.
 */
public class LongestStreak {

    public static int[] longestStreak(int[] costs, long budget) {
        int bestLength = 0;
        int bestStart = -1;
        long sum = 0;
        int left = 0;
        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];
            while (sum > budget) {
                sum -= costs[left++];
            }
            int length = right - left + 1;
            // Strictly greater keeps the earliest start on ties.
            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }
        return new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(longestStreak(new int[]{4, 2, 1, 7, 3, 1, 2, 1, 5}, 8)));
        System.out.println(Arrays.toString(longestStreak(new int[]{9, 10}, 8)));
    }
}
