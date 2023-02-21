import java.util.ArrayList;
import java.util.List;

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
 * Example 2:
 * <p>
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 * <p>
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 * <p>
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
     * Mutate first num with each bit, then exclude candidates with remained n-1 nums.
     * Use binary search
     * O(N^2logN)
     */
    public String findDifferentBinaryString(String[] nums) {
        List<String> candidate = new ArrayList<>();
        int n = nums.length;
        if (n == 1) {
            return nums[0].equals("0") ? "1" : "0";
        }

        char[] origin = new char[n];
        nums[0].getChars(0, n, origin, 0);
        //Mutate first number, O(N)
        for (int i = 0; i < n; i++) {
            if (origin[i] == '1') {
                origin[i] = '0';
                candidate.add(String.valueOf(origin));
                origin[i] = '1';
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (origin[i] == '0') {
                origin[i] = '1';
                candidate.add(String.valueOf(origin));
                origin[i] = '0';
            }
        }

        //O(N) nums with O(NlogN) avg test time
        for (int i = 1; i < n; i++) {
            //binary search, O(N) compare with O(logN) search
            int idx = binarySearch(candidate, nums[i], n);
            if (idx != -1) {
                candidate.remove(idx);
            }
        }

        return candidate.get(0);

    }

    private int binarySearch(List<String> bins, String tgt, int n) {
        int st = 0, ed = bins.size() - 1;
        int mid;
        while (st <= ed) {
            mid = (st + ed) / 2;
            int res = compareBinary(bins.get(mid), tgt, n);
            if (res == 0)
                return mid;
            else {
                if (res < 0) {
                    st = mid + 1;
                } else {
                    ed = mid - 1;
                }
            }
        }
        return -1;
    }

    private int compareBinary(String s1, String s2, int n) {
        int i = 0;
        while (i < n) {
            if (s1.charAt(i) == s2.charAt(i))
                i++;
            else
                break;
        }
        return i == n ? 0 : (s1.charAt(i) - s2.charAt(i));
    }
}
