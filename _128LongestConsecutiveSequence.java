import java.util.*;

/**
 * 128. Longest Consecutive Sequence
 * Medium
 * <p>
 * <p>
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 * You must write an algorithm that runs in O(n) time.
 */
public class _128LongestConsecutiveSequence {

    //使用哈希表存数字，从头找起
    public int longestConsecutive(int[] nums) {
        int res = 0, temp;
        int[] len = new int[nums.length];
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {
            if (!set.contains(num - 1)) {
                temp = 1;
                while (set.contains(num + 1)) {
                    temp++;
                    num++;
                }
                res = Math.max(res, temp);
            }
        }

        return res;
    }


}
