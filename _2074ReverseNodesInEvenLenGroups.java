/**
 * 2074. Reverse Nodes in Even Length Groups
 * Medium
 * <p>
 * You are given the head of a linked list.
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
 * <p>
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 * <p>
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: head = [5,2,6,3,9,1,7,3,8,4]
 * Output: [5,6,2,3,9,1,4,8,3,7]
 * Explanation:
 * - The length of the first group is 1, which is odd, hence no reversal occurs.
 * - The length of the second group is 2, which is even, hence the nodes are reversed.
 * - The length of the third group is 3, which is odd, hence no reversal occurs.
 * - The length of the last group is 4, which is even, hence the nodes are reversed.
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,1,0,6]
 * Output: [1,0,1,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 1. No reversal occurs.
 * <p>
 * Example 3:
 * <p>
 * Input: head = [1,1,0,6,5]
 * Output: [1,0,1,5,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 2. The nodes are reversed.
 * <p>
 * Example 4:
 * <p>
 * Input: head = [2,1]
 * Output: [2,1]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the last group is 1. No reversal occurs.
 * <p>
 * Example 5:
 * <p>
 * Input: head = [8]
 * Output: [8]
 * Explanation: There is only one group whose length is 1. No reversal occurs.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 105].
 * 0 <= Node.val <= 105
 */
public class _2074ReverseNodesInEvenLenGroups {

    /**
     * 使用三个指针即可解决，先数下一组的长度比使用stack/deque来undo的速度快得多。
     */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        int count, round = 2;
        ListNode cur = head, pre = head, tail = head.next;
        ListNode res = head;

        //In each iteration, head, pre, cur stands on the last node of current group.
        //Tail is the first node of next group
        while (tail != null) {
            count = 0;

            //count next group length
            while (count < round && tail != null) {
                cur = tail;
                tail = tail.next;
                count++;
            }

            //even-length group should be reversed
            if ((count & 1) == 0) {
                //reset cur and tail
                cur = head;
                tail = cur.next;

                //tail goes forward, cur follows tail and reconnect to pre
                for (int i = 0; i < count; i++) {
                    cur = tail;
                    tail = tail.next;

                    if (pre != head) {
                        cur.next = pre;
                    }
                    pre = cur;
                }

                //connect tails and heads between groups
                cur = head.next;
                head.next = pre;
                cur.next = tail;

            }

            pre = cur;
            head = cur;
            round++;
        }

        return res;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
            this(0, null);
        }

        public ListNode(int n) {
            this(n, null);
        }

        ListNode(int n, ListNode next) {
            this.val = n;
            this.next = next;
        }

    }


}
