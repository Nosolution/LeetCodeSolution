import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2054. Two Best Non-Overlapping Events
 * Medium
 * <p>
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei].
 * The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei.
 * You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 * Return this maximum sum.
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time.
 * More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 * <p>
 * Example 2:
 * <p>
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 * <p>
 * Example 3:
 * <p>
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= events.length <= 10^5
 * events[i].length == 3
 * 1 <= startTime_i <= endTime_i <= 10^9
 * 1 <= value_i <= 10^6
 */
public class _2054TwoBestNonOverlappingEvents {

    /**
     * 动态规划和堆应用
     */
    public int maxTwoEvents(int[][] events) {
        //按开始时间排序
        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        //比较结束时间的最小堆
        Queue<int[]> minHeap = new PriorityQueue<>(events.length, Comparator.comparing(o -> o[1]));

        int max = 0, res = 0;
        //对于event_i，先前遍历过的事件已按结束事件排序，从小到大查看找出时间合适且最大的value。
        //对于event_(i+1)，因为已经按开始时间排序，所以对i找到的max对于i+1也同样成立，继续更新就行
        for (int[] event : events) {
            while (!minHeap.isEmpty() && minHeap.peek()[1] < event[0]) {
                max = Math.max(max, minHeap.poll()[2]);
            }
            minHeap.add(event);
            res = Math.max(res, max + event[2]);
        }

        return res;
    }


}
