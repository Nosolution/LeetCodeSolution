import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 1654. Minimum Jumps to Reach Home
 * Medium
 * <p>
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 * <p>
 * The bug jumps according to the following rules:
 * <p>
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 * <p>
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i],
 * and integers a, b, and x, return the minimum number of jumps needed for the bug to reach its home. If there is no possible sequence of jumps that lands the bug on position x, return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: forbidden = [14,4,18,1,15], a = 3, b = 15, x = 9
 * Output: 3
 * Explanation: 3 jumps forward (0 -> 3 -> 6 -> 9) will get the bug home.
 * Example 2:
 * <p>
 * Input: forbidden = [8,3,16,6,12,20], a = 15, b = 13, x = 11
 * Output: -1
 * Example 3:
 * <p>
 * Input: forbidden = [1,6,2,14,5,17,4], a = 16, b = 9, x = 7
 * Output: 2
 * Explanation: One jump forward (0 -> 16) then one jump backward (16 -> 7) will get the bug home.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= forbidden.length <= 1000
 * 1 <= a, b, forbidden[i] <= 2000
 * 0 <= x <= 2000
 * All the elements in forbidden are distinct.
 * Position x is not forbidden.
 */
public class _1654MinimumJumpsToReachHome {


    /**
     * 好吧，其实这就是某种程度上的迷宫最短路径问题，在加上点dp，用层级遍历最快
     */
    public int minimumJumps(int[] forbidden, int a, int b, int x) throws IOException {
        int bound = 2000 + a + b;

        int[] dp = new int[bound];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int forbid : forbidden) {
            if (forbid < bound) {
                dp[forbid] = -1;
            }
        }
        dp[0] = 0;

        Queue<Pair<Integer, Boolean>> queue = new LinkedList<>(); //<pos, isBackward>
        queue.add(new Pair<>(0, false));

        Pair<Integer, Boolean> pair;
        int cur, size;
        while (!queue.isEmpty()) {
            size = queue.size();
            for (int i = 0; i < size; i++) {
                pair = queue.poll();
                cur = pair.getKey();
                if (cur == x) {
                    return dp[x];
                }
                if (!pair.getValue() && inBound(dp, cur - b)) {
                    dp[cur - b] = Math.min(dp[cur - b], dp[cur] + 1);
                    queue.add(new Pair<>(cur - b, true));
                }
                if (inBound(dp, cur + a)) {
                    dp[cur + a] = Math.min(dp[cur + a], dp[cur] + 1);
                    queue.add(new Pair<>(cur + a, false));
                }
            }
        }

        return dp[x] == Integer.MAX_VALUE ? -1 : dp[x];

    }

    private boolean inBound(int[] dp, int pos) {
        return pos > 0 && pos < dp.length && dp[pos] == Integer.MAX_VALUE;
    }


}




