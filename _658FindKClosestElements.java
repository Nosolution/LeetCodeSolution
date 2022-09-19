import java.util.*;

/**
 * 658. Find K Closest Elements
 * Medium
 * <p>
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 * <p>
 * An integer a is closer to x than an integer b if:
 * <p>
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 * <p>
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class _658FindKClosestElements {

    //binary search
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int st = 0, ed = arr.length - 1;
        int mid = (st + ed) / 2;
        while (st < mid) {
            if (arr[mid] < x) {
                st = mid;
            } else {
                ed = mid;
            }
            mid = (st + ed) / 2;
        }

        //window (st, ed)
        while (ed - st - 1 < k) {
            if (ed < arr.length && st >= 0) {
                if (arr[ed] - x < x - arr[st]) {
                    ed += 1;
                } else {
                    st -= 1;
                }
            } else if (st >= 0) {
                st -= 1;
            } else if (ed < arr.length) {
                ed += 1;
            } else {
                break;
            }
        }

        List<Integer> res = new LinkedList<>();
        for (int i = st + 1; i <= ed - 1; i++) {
            res.add(arr[i]);
        }

        return res;
    }


    public static void main(String[] args) {

    }
}