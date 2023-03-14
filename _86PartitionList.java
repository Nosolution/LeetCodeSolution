/**
 * 86. Partition List
 * Medium
 * <p>
 * <p>
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 * <p>
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class _86PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode less = new ListNode(0), ge = new ListNode(0);
        ListNode lesp = less, gep = ge;

        while (head != null) {
            if (head.val < x) {
                lesp.next = head;
                lesp = lesp.next;
            } else {
                gep.next = head;
                gep = gep.next;
            }
            head = head.next;
        }

        gep.next = null;
        lesp.next = ge.next;
        return less.next;
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




