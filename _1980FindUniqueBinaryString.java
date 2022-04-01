import java.util.*;

/**
 * 1980. Find Unique Binary String
 * Medium
 * <p>
 * Given an array of strings nums containing n unique binary strings each of length n,
 * return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] is either '0' or '1'.
 * All the strings of nums are unique.
 */
public class _1980FindUniqueBinaryString {

    /**
     * 将binary转为int，因为长度n的二进制数有2^n个，因此排序后找到不连续的就是结果
     */
    public String findDifferentBinaryString(String[] nums) {
        int n = nums.length;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (String bin : nums) {
            heap.add(binary2Int(bin));
        }
        int cmp = 0;
        while (!heap.isEmpty() && heap.peek() == cmp) {
            cmp++;
            heap.poll();
        }
        return int2Binary(cmp, n);
    }

    private int binary2Int(String bin) {
        char[] c = bin.toCharArray();
        int res = c[0] - '0';
        for (int i = 1; i < c.length; i++) {
            res *= 2;
            res += c[i] - '0';
        }
        return res;
    }

    private String int2Binary(int num, int n) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 2);
            num /= 2;
        }
        while (sb.length() < n) {
            sb.append(0);
        }
        return sb.reverse().toString();
    }


}
