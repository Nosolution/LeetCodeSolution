import java.util.HashMap;

/**
 * 1338. Reduce Array Size to The Half
 * Medium
 * <p>
 * <p>
 * You are given an integer array arr. You can choose a set of integers and remove all the occurrences of these integers in the array.
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has a size greater than half of the size of the old array.
 * Example 2:
 * <p>
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 */
public class _1338ReduceArraySizeToTheHalf {


    public int minSetSize(int[] arr) {
        int n = arr.length;
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int x : arr) {
            counter.put(x, counter.getOrDefault(x, 0) + 1);
        }

        //使用桶记录每个freq的计数，counter已经有消耗了O(N)内存，所以无所谓了
        int[] freq_count = new int[n + 1];
        for (int freq : counter.values())
            freq_count[freq]++;

        int res = 0, removed = 0, half = n / 2, freq = n;
        while (removed < half) {
            res += 1;
            while (freq_count[freq] == 0)
                --freq;
            removed += freq;
            --freq_count[freq];
        }
        return res;
    }

}


