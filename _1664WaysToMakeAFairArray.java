import java.util.*;

/**
 * 1664. Ways to Make a Fair Array
 * Medium
 * <p>
 * You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. Notice that the index of the elements may change after the removal.
 * <p>
 * For example, if nums = [6,1,7,4,1]:
 * <p>
 * Choosing to remove index 1 results in nums = [6,7,4,1].
 * Choosing to remove index 2 results in nums = [6,1,4,1].
 * Choosing to remove index 4 results in nums = [6,1,7,4].
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
 * <p>
 * Return the number of indices that you could choose such that after the removal, nums is fair.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,6,4]
 * Output: 1
 * Explanation:
 * Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
 * Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
 * Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
 * Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
 * There is 1 index that you can remove to make nums fair.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 3
 * Explanation: You can remove any index and the remaining array is fair.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: You cannot make a fair array after removing any index.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 */
public class _1664WaysToMakeAFairArray {


    /**
     * 右边的odd会转为左边的even，反过来也是一样的，因此遍历一遍就行了。
     * 这叫什么，动态规划？
     */
    public int waysToMakeFair(int[] nums) {
        int res = 0;
        int evenSum = 0, oddSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) != 1) {
                evenSum += nums[i];
            } else {
                oddSum += nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) == 1) {
                oddSum -= nums[i];
                if (oddSum == evenSum)
                    res++;
                evenSum += nums[i];
            } else {
                evenSum -= nums[i];
                if (oddSum == evenSum)
                    res++;
                oddSum += nums[i];
            }
        }

        return res;
    }

}




