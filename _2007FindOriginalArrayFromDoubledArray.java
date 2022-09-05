import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2007. Find Original Array From Doubled Array
 * Medium
 * <p>
 * <p>
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element
 * in original, and then randomly shuffling the resulting array.
 * <p>
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return
 * an empty array. The elements in original may be returned in any order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 * <p>
 * Example 2:
 * <p>
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * <p>
 * Example 3:
 * <p>
 * Input: changed = [1]
 * Output: []
 * Explanation: changed is not a doubled array.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= changed.length <= 10^5
 * 0 <= changed[i] <= 10^5
 */
public class Solution {


    public int[] findOriginalArray(int[] changed) {
        if (changed.length % 2 != 0)
            return new int[0];

        //O(n*log(n))
        Arrays.sort(changed);

        int[] origin = new int[changed.length / 2];
        int originSize = 0, orgIdx = 0, chgIdx = 1;

        Queue<Integer> staged = new LinkedList<>();

        //O(n)
        while (originSize < origin.length && chgIdx < changed.length) {
            if (changed[orgIdx] * 2 == changed[chgIdx]) {
                origin[originSize] = changed[orgIdx];
                staged.add(chgIdx);
                originSize++;

                orgIdx++;
                chgIdx++;
                while (!staged.isEmpty() && orgIdx == staged.peek()) {
                    staged.poll();
                    orgIdx++;
                    chgIdx = Math.max(orgIdx + 1, chgIdx);
                }

            } else if (changed[orgIdx] * 2 > changed[chgIdx]) {
                chgIdx++;
            } else {
                return new int[0];
            }
        }

        if (originSize != origin.length)
            return new int[0];
        else
            return origin;
    }



}
