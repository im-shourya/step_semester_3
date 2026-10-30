package data_structures.assigment_problems;

/*
 * Linear scan: O(n) time, O(1) space.
 * Two boundary binary searches: O(log n) time, O(1) space.
 * A plain binary search may land in the middle of a run of equal marks, so each search keeps
 * narrowing until it finds the first index of the boundary instead of stopping at any match.
 */
public class ScoreBandCounter {

    // First index whose score is >= target (scores.length if none).
    private static int lowerBound(int[] scores, int target) {
        int lo = 0;
        int hi = scores.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (scores[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    public static int countInBand(int[] scores, int low, int high) {
        return lowerBound(scores, high + 1) - lowerBound(scores, low);
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));
        System.out.println(countInBand(scores, 90, 100));
    }
}
