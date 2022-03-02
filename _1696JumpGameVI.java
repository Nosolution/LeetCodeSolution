import java.util.*;

/**
 * 1696. Jump Game VI
 * Medium
 * <p>
 * You are given a 0-indexed integer array nums and an integer k.
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array.
 * That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * Return the maximum score you can get.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3]. The sum is 7.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3]. The sum is 17.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length, k <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class _1696JumpGameVI {


    public int maxResult(int[] nums, int k) {
        return doMaxResult1(nums, k);
    }

    /**
     * 暴力dp，使用递归，时间复杂度O(n*k)
     */
    private int doMaxResult1(int[] nums, int k) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = nums[0];
        return recursive(nums, k, dp, nums.length - 1);
    }

    private int recursive(int[] nums, int k, int[] dp, int ed) {
        if (dp[ed] == Integer.MIN_VALUE) {
            int res = Integer.MIN_VALUE;
            for (int i = 1; i <= k && ed - i >= 0; i++) {
                res = Math.max(res, recursive(nums, k, dp, ed - i));
            }
            dp[ed] = res + nums[ed];
        }
        return dp[ed];
    }

    /**
     * 使用heap保存最大dp[i]，从右向左遍历，维持heap顶上元素是在当前元素的k范围内
     */
    public int doMaxResult2(int[] nums, int k) {
        int[] dp = new int[nums.length];
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> dp[o2] - dp[o1]);
        dp[nums.length - 1] = nums[nums.length - 1];
        heap.add(nums.length - 1);

        int i = nums.length - 2;
        while (i >= 0) {
            while (heap.peek() > i + k) {
                heap.poll();
            }
            dp[i] = nums[i] + dp[heap.peek()];
            heap.add(i);
            i--;
        }
        return dp[0];

    }

    /**
     * 降序队列法，以dp[i]作为最小标准抛除队列尾部的元素，保持队列顶部元素在k范围内
     */
    private int doMaxResult3(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = (deque.isEmpty() ? 0 : dp[deque.peekFirst()]) + nums[i];
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()])
                deque.pollLast();
            deque.addLast(i);
            while (!deque.isEmpty() && i - deque.peekFirst() >= k)
                deque.pollFirst();
        }

        return dp[n - 1];
    }


}
