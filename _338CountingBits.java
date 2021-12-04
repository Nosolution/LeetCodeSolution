/**
 * 338. Counting Bits
 * Easy
 * <p>
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * <p>
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: [0,1,1,2,1,2]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 10^5
 */
public class _338CountingBits {


    /**
     * 假设数字n有k个1，则2*n=n<<1，1的数量不变；因此对于偶数n，arr[n]=arr[n/2]，对于奇数n，arr[n]=arr[n/2]+1。
     * 对于每个起始基数b(从2开始)，如果不知道arr[b]，则从arr[b/2]处求得；接着更新全部arr[2^i*b]。
     * 使用位运算加速
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        int b = 0, k;
        //set arr[0] and arr[1]
        while (b <= n && b < 2) {
            res[b] = b;
            b++;
        }

        while (b <= n) {
            //update base b
            if (res[b] == 0) {
                res[b] = res[b >> 1] + (b & 1);
            }

            //set all k = 2^i*b
            for (k = b << 1; k <= n; k = k << 1) {
                res[k] = res[b];
            }

            b++;
        }

        return res;

    }
}
