/**
 * 152. Maximum Product Subarray
 * Medium
 * <p>
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 * It is guaranteed that the answer will fit in a 32-bit integer.
 * A subarray is a contiguous subsequence of the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -10 <= nums[i] <= 10
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 */

public class _152MaximumProductSubarray {
    /**
     * 因为是求最大积且数字为整数，子数组包含的数更多一定绝对值更大。
     * 先贪婪计算每个不包含0的子数组的积，如果结果为负数再去掉两端乘积绝对值更小的负数区间。
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int prod;
        int st = 0, ed = 0;
        int firstNeg, lastNeg;

        while (st < nums.length && nums[st] == 0) {
            st++;
        }
        if (st >= nums.length || nums.length == 1) {
            return max;
        }

        do {
            //reset var
            ed = st;
            prod = 1;
            firstNeg = -1;
            lastNeg = -1;

            //none-zero interval
            while (ed < nums.length && nums[ed] != 0) {
                if (nums[ed] < 0) {
                    if (firstNeg < 0) {
                        firstNeg = ed;
                    }
                    lastNeg = ed;
                }
                prod *= nums[ed];
                ed++;
            }

            if (prod < 0) {
                if (ed - st > 1) {
                    int pre = product(st, firstNeg + 1, nums);
                    int suf = product(lastNeg, ed, nums);
                    prod /= Math.max(suf, pre);
                } else {
                    prod = nums[st];
                }
            }

            max = Math.max(max, prod);
            st = ed + 1;

            //find first none-zero
            while (st < nums.length && nums[st] == 0) {
                st++;
            }

            if (ed < nums.length) {
                //at this time nums[ed] is always 0
                max = Math.max(max, nums[ed]);
            }

        } while (st < nums.length);


        return max;
    }

    private int product(int st, int ed, final int[] nums) {
        int prod = 1;
        for (int i = st; i < ed; i++) {
            prod *= nums[i];
        }
        return prod;
    }
}
