import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * 646. Maximum Length of Pair Chain
 * Medium
 * <p>
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 * Return the length longest chain which can be formed.
 * You do not need to use up all the given intervals. You can select pairs in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * <p>
 * Example 2:
 * <p>
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= left_i < right_i <= 1000
 */
public class _646MaximumLengthofPairChain {

    /**
     * 将pairs排序好之后，新到来的pair仅可能与最后一个pair冲突。
     * 假如pairs[i]与pairs[prev]冲突，说明pairs[i][0]<=pairs[pre][1]，
     * 而排序后必有pair[cur][0]<=pairs[i][0]，且cur在链中，因此不成立。
     * 所以只需比较新pair与最后一个pair，可以产生链接与替换行为。
     */
    public int findLongestChain(int[][] pairs) {
        //按第一项和第二项排序
        Arrays.sort(pairs, (o1, o2) -> {
            if (o1[0] == o2[0])
                return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        int pre = -1, cur = 0, res = 1;
        for (int i = 1; i < pairs.length; i++) {
            //如果能刚好接上，+1
            if (pairs[i][0] > pairs[cur][1]) {
                pre = cur;
                cur = i;
                res++;
            } else if (pairs[i][1] < pairs[cur][1]) {
                //如果第二项小于原尾部pair的第二项，则替换
                cur = i;
            }
        }


        return res;
    }


}
