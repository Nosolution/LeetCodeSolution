/**
 * 2091. Removing Minimum and Maximum From Array
 * Medium
 * <p>
 * You are given a 0-indexed array of distinct integers nums.
 * There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 * A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
 * Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,10^,7,5,4,1^,8,6]
 * Output: 5
 * Explanation:
 * The minimum element in the array is nums[5], which is 1.
 * The maximum element in the array is nums[1], which is 10.
 * We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
 * This results in 2 + 3 = 5 deletions, which is the minimum number possible.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,-4^,19^,1,8,-2,-3,5]
 * Output: 3
 * Explanation:
 * The minimum element in the array is nums[1], which is -4.
 * The maximum element in the array is nums[2], which is 19.
 * We can remove both the minimum and maximum by removing 3 elements from the front.
 * This results in only 3 deletions, which is the minimum number possible.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [101^]
 * Output: 1
 * Explanation:
 * There is only one element in the array, which makes it both the minimum and maximum element.
 * We can remove it with 1 deletion.
 */
public class _2091RemovingMinimumAndMaximum {

    /**
     * 根据直觉，用变量记录最大最小值和下标就完事了，测了下好像性能也超过平均值了，搞不懂
     */
    public int minimumDeletions(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, mini = 0, maxi = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                mini = i;
            }
            if (nums[i] > max) {
                max = nums[i];
                maxi = i;
            }
        }

        int a, b;
        if (mini > maxi) {
            a = maxi;
            b = mini;
        } else {
            a = mini;
            b = maxi;
        }


        return Math.min(a + 1 + nums.length - b, Math.min(b + 1, nums.length - a));
    }
}
