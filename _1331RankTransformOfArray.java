import java.util.*;


/**
 * 1331. Rank Transform of an Array
 * Easy
 * <p>
 * <p>
 * Given an array of integers arr, replace each element with its rank.
 * <p>
 * The rank represents how large the element is. The rank has the following rules:
 * <p>
 * Rank is an integer starting from 1.
 * The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
 * Rank should be as small as possible.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [40,10,20,30]
 * Output: [4,1,2,3]
 * Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
 * Example 2:
 * <p>
 * Input: arr = [100,100,100]
 * Output: [1,1,1]
 * Explanation: Same elements share the same rank.
 * Example 3:
 * <p>
 * Input: arr = [37,12,28,9,100,56,80,5,12]
 * Output: [5,3,4,2,8,6,7,1,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= arr.length <= 10^5
 * -10^9 <= arr[i] <= 10^9
 */
public class Solution {

    //优化后的方法
    public int[] arrayRankTransform(int[] arr) {
        int[] cp = Arrays.copyOf(arr, arr.length);
        Arrays.sort(cp);
        int rk = 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : cp) {
            if (!map.containsKey(i)) {
                map.put(i, rk);
                rk++;
            }
        }

        //是用arr而不是新建res数组可以提高约20%的速度
        for (int i = 0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }


    //使用堆来计算，效率很低，原因可能是堆排序远不如数组快排迅速
    public int[] arrayRankTransform1(int[] arr) {
        int[] res = new int[arr.length];

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(i -> arr[i]));
        for (int i = 0; i < arr.length; i++) {
            heap.add(i);
        }

        int rk = 0, last = -1;
        while (!heap.isEmpty()) {
            int idx = heap.poll();
            if (arr[idx] != last) {
                rk++;
            }
            res[idx] = rk;
            last = arr[idx];
        }

        return res;
    }


    public static void main(String[] args) {

    }
}