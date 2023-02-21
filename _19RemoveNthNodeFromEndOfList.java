

/**
 * 19. Remove Nth Node From End of List
 * Medium
 * <p>
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 * <p>
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class _19RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = calcLength(head);
        if (length == n) {
            return head.next;
        }
        int ct = length - n;
        ListNode node = new ListNode(0, head);
        while (ct != 0) {
            node = node.next;
            ct--;
        }
        node.next = node.next.next;
        return head;
    }

    private int calcLength(ListNode head) {
        int l = 0;
        while (head != null) {
            l += 1;
            head = head.next;
        }
        return l;
    }

}

