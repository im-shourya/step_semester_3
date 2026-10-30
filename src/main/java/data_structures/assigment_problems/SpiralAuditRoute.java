package data_structures.assigment_problems;

import java.util.ArrayList;
import java.util.List;

/*
 * Time O(m * n): every bin is visited once. Extra space O(1) excluding the output list.
 */
public class SpiralAuditRoute {

    public static List<Integer> auditRoute(int[][] grid) {
        List<Integer> route = new ArrayList<>();
        int top = 0;
        int bottom = grid.length - 1;
        int left = 0;
        int right = grid[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int c = left; c <= right; c++) {
                route.add(grid[top][c]);
            }
            top++;
            for (int r = top; r <= bottom; r++) {
                route.add(grid[r][right]);
            }
            right--;
            // Without these checks a single leftover row or column would be walked twice.
            if (top <= bottom) {
                for (int c = right; c >= left; c--) {
                    route.add(grid[bottom][c]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int r = bottom; r >= top; r--) {
                    route.add(grid[r][left]);
                }
                left++;
            }
        }
        return route;
    }

    public static void main(String[] args) {
        System.out.println(auditRoute(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
        System.out.println(auditRoute(new int[][]{{1}, {2}, {3}}));
        System.out.println(auditRoute(new int[][]{{1, 2, 3}}));
    }
}
