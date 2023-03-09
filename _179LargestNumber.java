import java.util.*;


/**
 * 179. Largest Number
 * Medium
 * <p>
 * <p>
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 * Since the result may be very large, so you need to return a string instead of an integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 * <p>
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 109
 */
public class _179LargestNumber {


    public String largestNumber(int[] nums) {
        char[][] chars = new char[nums.length][];
        for (int i = 0; i < nums.length; i++) {
            chars[i] = ("" + nums[i]).toCharArray();
        }

        Arrays.sort(chars, this::compare);
        StringBuilder sb = new StringBuilder();
        for (char[] num : chars) {
            sb.append(num);
        }

        int zi = 0;
        while (zi < sb.length() - 1 && sb.charAt(zi) == '0') {
            zi++;
        }

        return sb.substring(zi);
    }

    private int compare(char[] s1, char[] s2) {

        char[] tmp;
        int flag = 1;
        if (s2.length < s1.length) {
            tmp = s1;
            s1 = s2;
            s2 = tmp;
            flag = -1;
        }


        int i = 0;
        while (i < s1.length) {

            if (s1[i] - s2[i] != 0) {
                return flag * (s2[i] - s1[i]);
            }
            i++;
        }

        int j = 0;
        while (i < s2.length) {
            if (s2[i] - s2[j] != 0) {
                return flag * (s2[i] - s2[j]);
            }
            i++;
            j++;
        }

        i = 0;
        while (j < s2.length) {
            if (s1[i] - s2[j] != 0) {
                return flag * (s1[i] - s2[j]);
            }
            i++;
            j++;
        }
        return 0;
    }


}




