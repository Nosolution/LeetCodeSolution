import java.util.*;

/**
 * 1851. Minimum Interval to Include Each Query
 * Hard
 * <p>
 * <p>
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive). The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 * You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti. If no such interval exists, the answer is -1.
 * Return an array containing the answers to the queries.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 * Example 2:
 * <p>
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 */
public class _1851MinimumIntervalToIncludeEachQuery {

    /**
     * 对intervals的开始时间和queries做排序，使用堆根据长度对开始时间小于当前q的it进行排序。
     * 顶上结束时间过早的it被丢弃
     */
    public int[] minInterval(int[][] intervals, int[] queries) {

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int[] qcopy = queries.clone();
        Arrays.sort(qcopy);
        Map<Integer, Integer> qsizeMap = new HashMap<>();

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(i -> i[1] - i[0]));
        int i = 0;
        for (int q : qcopy) {
            while (i < intervals.length && intervals[i][0] <= q) {
                heap.add(intervals[i]);
                i++;
            }

            while (!heap.isEmpty() && heap.peek()[1] < q) {
                heap.poll();
            }

            if (heap.isEmpty()) {
                qsizeMap.put(q, -1);
            } else {
                int[] it = heap.peek();
                qsizeMap.put(q, it[1] - it[0] + 1);
            }
        }

        int[] res = new int[queries.length];
        for (int j = 0; j < res.length; j++) {
            res[j] = qsizeMap.get(queries[j]);
        }

        return res;
    }


}
