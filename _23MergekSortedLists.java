import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 23. Merge k Sorted Lists
 * Hard
 * <p>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 10^4.
 */
public class _23MergekSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }

        ListNode head = new ListNode(0);
        ListNode cur = head;

        while (!heap.isEmpty()) {
            cur.next = heap.poll();
            cur = cur.next;

            if (cur.next != null) {
                heap.add(cur.next);
            }
        }

        return head.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}




