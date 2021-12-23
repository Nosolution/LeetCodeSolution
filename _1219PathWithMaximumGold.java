import java.util.Arrays;
import java.util.Comparator;


/**
 * 1219. Path with Maximum Gold
 * Medium
 * <p>
 * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 * - Every time you are located in a cell you will collect all the gold in that cell.
 * - From your position, you can walk one step to the left, right, up, or down.
 * - You can't visit the same cell more than once.
 * - Never visit a cell with 0 gold.
 * - You can start and stop collecting gold from any position in the grid that has some gold.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 * [5,8,7],
 * [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 * <p>
 * Example 2:
 * <p>
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 * [2,0,6],
 * [3,4,5],
 * [0,3,0],
 * [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 */
public class _1219PathWithMaximumGold {

    boolean[][] visited;
    int m, n;


    //dfs就完事了
    public int getMaximumGold(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                res = Math.max(res, visit(i, j, grid));
            }
        }

        return res;
    }

    private int visit(int p, int q, final int[][] grid) {
        if (p >= m || p < 0)
            return 0;
        if (q >= n || q < 0)
            return 0;
        if (visited[p][q]) {
            return 0;
        }
        if (grid[p][q] == 0) {
            return 0;
        }

        int max = 0;
        visited[p][q] = true;
        max = Math.max(max, visit(p + 1, q, grid));
        max = Math.max(max, visit(p - 1, q, grid));
        max = Math.max(max, visit(p, q + 1, grid));
        max = Math.max(max, visit(p, q - 1, grid));
        visited[p][q] = false;
        return max + grid[p][q];

    }


}
