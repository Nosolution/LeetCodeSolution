import java.util.Stack;


/**
 * 1717. Maximum Score From Removing Substrings
 * Medium
 * <p>
 * you are given a string s and two integers x and y. You can perform two types of operations any number of times.
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 * Example 2:
 * <p>
 * Input: s = "aabbaaxybbaabb", x = 5, y = 4
 * Output: 20
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * 1 <= x, y <= 10^4
 * s consists of lowercase English letters.
 */
public class _1717MaximumScoreFromRemovingSubstrings {

    public int maximumGain(String s, int x, int y) {
        Stack<Character> stk = new Stack<>();
        char prm, sub;
        if (x > y) {
            prm = 'a';
            sub = 'b';
        } else {
            prm = 'b';
            sub = 'a';
            x ^= y;
            y ^= x;
            x ^= y;
        }

        char[] cs = s.toCharArray();
        int i = 0, res = 0;
        while (i < s.length()) {
            if (cs[i] == 'a' || cs[i] == 'b') {
                int j = i + 1, cur = 0;
                stk.push(cs[i]);
                while (!stk.isEmpty() && j < s.length() && (cs[j] == 'a' || cs[j] == 'b')) {
                    if (stk.peek() == prm && cs[j] == sub) {
                        cur += Math.max(x, y);
                        stk.pop();
                    } else {
                        stk.push(cs[j]);
                    }
                    j++;
                }
                if (!stk.isEmpty()) {
                    int ct = 0;
                    while (!stk.isEmpty() && stk.peek() == prm) {
                        stk.pop();
                        ct++;
                    }
                    cur += Math.min(ct, stk.size()) * y;
                    stk.clear();
                }
                res += cur;
                i = j;
            } else {
                i++;
            }
        }


        return res;
    }


}




