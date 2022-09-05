import java.util.*;

/**
 * 2089. Find Target Indices After Sorting Array
 * Easy
 * <p>
 * You are given a 0-indexed integer array nums and a target element target.
 * <p>
 * A target index is an index i such that nums[i] == target.
 * <p>
 * Return a list of the target indices of nums after sorting nums in non-decreasing order.
 * If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 */
public class Solution {


    //O(log(n))
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        List<Integer> res = new ArrayList<>();
        int st = findStart(nums, target);
        int ed = findEnd(nums, target);

        if (st != -1 && ed != -1) {
            for (int i = st; i <= ed; i++) {
                res.add(i);
            }
        }
        return res;
    }

    private int findStart(int[] nums, int target) {
        int st = 0, ed = nums.length - 1;
        if (nums[st] == target)
            return st;
        else if (nums[ed] < target || nums[st] > target || st == ed)
            return -1;

        int mid = (st + ed + 1) / 2;

        while (mid < ed) {
            if (nums[mid] < target) {
                st = mid;
            } else if (nums[mid] > target) {
                ed = mid;
            } else {
                //nums[mid]==target, nums[mid-1] <= target
                if (nums[mid - 1] == target) {
                    ed = mid - 1;
                } else {
                    return mid;
                }
            }
            mid = (st + ed + 1) / 2;
        }

        if (nums[mid - 1] < target && nums[mid] == target)
            return mid;
        else
            return -1;
    }

    private int findEnd(int[] nums, int target) {
        int st = 0, ed = nums.length - 1;
        if (nums[ed] == target)
            return ed;
        else if (nums[ed] < target || nums[st] > target || st == ed)
            return -1;

        int mid = (st + ed) / 2;

        while (st < mid) {
            if (nums[mid] < target) {
                st = mid;
            } else if (nums[mid] > target) {
                ed = mid;
            } else {
                //nums[mid]==target, nums[mid+1] >= target
                if (nums[mid + 1] == target) {
                    st = mid + 1;
                } else {
                    return mid;
                }
            }
            mid = (st + ed) / 2;
        }

        if (nums[mid] == target && nums[mid + 1] > target)
            return mid;
        else
            return -1;
    }

}
