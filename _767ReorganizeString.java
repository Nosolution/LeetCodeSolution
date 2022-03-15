import javafx.util.Pair;

import java.util.*;

/**
 * 767. Reorganize String
 * Medium
 * <p>
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 * Return any possible rearrangement of s or return "" if not possible.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: "aba"
 * <p>
 * Example 2:
 * <p>
 * Input: s = "aaab"
 * Output: ""
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class _767ReorganizeString {

    /**
     * 用堆实现
     */
    public String reorganizeString(String s) {
        int[] dict = new int[26];
        for (char c : s.toCharArray()) {
            dict[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        PriorityQueue<Pair<Character, Integer>> heap = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (int i = 0; i < 26; i++) {
            if (dict[i] != 0) {
                heap.add(new Pair<>((char) ('a' + i), dict[i]));
            }
        }

        Pair<Character, Integer> cur, store = null;
        char last;
        cur = heap.poll();
        last = cur.getKey();
        res.append(last);
        if (cur.getValue() > 1) {
            heap.add(new Pair<>(cur.getKey(), cur.getValue() - 1));
        }

        while (!heap.isEmpty()) {
            store = null;
            if (heap.peek().getKey().equals(last)) {
                store = heap.poll();
            }
            if (heap.isEmpty())
                return "";
            cur = heap.poll();
            last = cur.getKey();
            res.append(last);
            if (store != null) {
                heap.add(store);
            }
            if (cur.getValue() > 1) {
                heap.add(new Pair<>(cur.getKey(), cur.getValue() - 1));
            }
        }


        return res.toString();
    }

}
