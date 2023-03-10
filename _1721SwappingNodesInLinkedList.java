/**
 * 1721. Swapping Nodes in a Linked List
 * Medium
 * <p>
 * You are given the head of a linked list, and an integer k.
 * Return the head of the linked list after swapping the values of the kth node from the beginning and the kth node from the end (the list is 1-indexed).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [7,9,6,6,7,8,3,0,9,5], k = 5
 * Output: [7,9,6,6,8,7,3,0,9,5]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 10……5
 * 0 <= Node.val <= 100
 */
public class _1721SwappingNodesInLinkedList {

    //快慢指针法
    public ListNode swapNodes(ListNode head, int k) {
        ListNode slow = head, fast = head;
        while (k > 1) {
            fast = fast.next;
            k--;
        }

        ListNode h = fast;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        if (h != slow) {
            h.val ^= slow.val;
            slow.val ^= h.val;
            h.val ^= slow.val;
        }
        return head;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}




