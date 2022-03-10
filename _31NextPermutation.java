import java.util.Arrays;

/**
 * 31. Next Permutation
 * Medium
 * <p>
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * <p>
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * <p>
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class _31NextPermutation {

    /**
     * i指示从右往左第一个比右边小的数字，i及右边的数字一定会构成下一个排列。
     * j指示从右往左第一个比i大的数字，nums[i]与nums[j]交换后的数一定比原来大，且i右边的数都是降序的，因此也是符合最小的。
     * 最后i右边（交换后）的数正序排序就得到下一个排列。
     */
    public void nextPermutation(int[] nums) {
        int i, j;
        loop:
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                for (j = nums.length - 1; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        nums[i] ^= nums[j];
                        nums[j] ^= nums[i];
                        nums[i] ^= nums[j];
                        Arrays.sort(nums, i + 1, nums.length);
                        break loop;
                    }
                }
            }
        }
        if (i == -1) {
            for (i = 0; i < nums.length - 1 - i; i++) {
                nums[i] ^= nums[nums.length - 1 - i];
                nums[nums.length - 1 - i] ^= nums[i];
                nums[i] ^= nums[nums.length - 1 - i];
            }
        }
    }

}
