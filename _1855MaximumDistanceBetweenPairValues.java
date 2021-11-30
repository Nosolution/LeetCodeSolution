/**
 * 1855. Maximum Distance Between a Pair of Values
 * Medium
 * <p>
 * You are given two non-increasing 0-indexed integer arrays nums1 and nums2.
 * A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i.
 * Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.
 * An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
 * Output: 2
 * Explanation: The valid pairs are (0,0), (2,2), (2,3), (2,4), (3,3), (3,4), and (4,4).
 * The maximum distance is 2 with pair (2,4).
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [2,2,2], nums2 = [10,10,1]
 * Output: 1
 * Explanation: The valid pairs are (0,0), (0,1), and (1,1).
 * The maximum distance is 1 with pair (0,1).
 * <p>
 * Example 3:
 * <p>
 * Input: nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
 * Output: 2
 * Explanation: The valid pairs are (2,2), (2,3), (2,4), (3,3), and (3,4).
 * The maximum distance is 2 with pair (2,4).
 * <p>
 * Example 4:
 * <p>
 * Input: nums1 = [5,4], nums2 = [3,2]
 * Output: 0
 * Explanation: There are no valid pairs, so return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[j] <= 105
 * Both nums1 and nums2 are non-increasing.
 */

public class _1855MaximumDistanceBetweenPairValues {
    /**
     * i和j都从0开始向后寻找合适位置，符合条件时j+1，不符合条件时i和j都+1，保证新的结果一定比先前结果更大。
     */
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int max = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums2[j] < nums1[i]) {
                i++;
            } else {
                //do so to ignore boundary conditions
                max = j - i;
            }
            j++;
        }
        return max;
    }
}
