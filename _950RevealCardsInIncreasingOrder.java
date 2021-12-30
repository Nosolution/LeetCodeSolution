import java.util.*;

/**
 * 950. Reveal Cards In Increasing Order
 * Medium
 * <p>
 * You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer on the ith card is deck[i].
 * You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
 * You will do the following steps repeatedly until all cards are revealed:
 * 1. Take the top card of the deck, reveal it, and take it out of the deck.
 * 2. If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
 * 3. If there are still unrevealed cards, go back to step 1. Otherwise, stop.
 * Return an ordering of the deck that would reveal the cards in increasing order.
 * Note that the first entry in the answer is considered to be the top of the deck.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: deck = [17,13,11,2,3,5,7]
 * Output: [2,13,3,11,5,17,7]
 * Explanation:
 * We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
 * After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
 * We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
 * We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
 * We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
 * We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
 * We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
 * We reveal 13, and move 17 to the bottom.  The deck is now [17].
 * We reveal 17.
 * Since all the cards revealed are in increasing order, the answer is correct.
 * <p>
 * Example 2:
 * <p>
 * Input: deck = [1,1000]
 * Output: [1,1000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= deck.length <= 1000
 * 1 <= deck[i] <= 10^6
 * All the values of deck are unique.
 */
public class _950RevealCardsInIncreasingOrder {

    /**
     * 靠直觉做出来的，还是挺神秘的。
     * 感觉有点像展开卷起的被子那样的过程，模拟一下就好了。
     * 根据观察，本质还是一个排序好的数组，根据观察可以看出每个排序好的数中间会夹着另外一个数，尚未抽出的数在抽出其他数的时候在不停地滚来滚去。
     * 创建一个长度为2n-1的数组，先前排序好的数字以大小为1的间隔填入数组中。
     * 从结尾递推回来，从窗口为1不断模拟到窗口为n，就是一个抽卡的逆过程，发现会有两个指针p和q，p从最后一个间隔遍历到第一个间隔，q从最后一个数遍历到中间。
     * 每次循环都是间隔p中填入q所指向的数。
     */
    public int[] deckRevealedIncreasing1(int[] deck) {
        Arrays.sort(deck);
        int[] l = new int[deck.length * 2 - 1];
        for (int i = 0; i < deck.length; i++) {
            l[i * 2] = deck[i];
        }
        for (int p = l.length - 2, q = l.length - 1; p > 0; p -= 2, q--) {
            l[p] = l[q];
        }

        return Arrays.copyOfRange(l, 0, deck.length);
    }


    /**
     * 上述算法的n空间版，从头递推，直接计算每个间隔最终要的原数组的数。
     * 对于位置i，是从第一个间隔2n-3递推(2n-3-i)/2次以后的结果，因此q指针指向的数的下标是2n-2-(2n-3-i)/2。可以发现如果该下标如果还是奇数，还要继续找。直到偶数为止。
     * 优化后的公式等于i = n-1+(1+i)/2。
     * 但速度和使用内存都劣于上一个方法。
     */
    public int[] deckRevealedIncreasing2(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] res = new int[n];
        for (int i = 0, t; i < n; i++) {
            t = i;
            while (t % 2 != 0) {
                t = n - 1 + (1 + t) / 2;
            }
            res[i] = deck[t / 2];
        }
        return res;
    }
}
