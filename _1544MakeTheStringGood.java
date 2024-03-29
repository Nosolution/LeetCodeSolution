import java.util.Stack;

/**
 * 1544. Make The String Great
 * Easy
 * <p>
 * <p>
 * Given a string s of lower and upper case English letters.
 * <p>
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 * <p>
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
 * <p>
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 * <p>
 * Notice that an empty string is also good.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 * Example 2:
 * <p>
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * Example 3:
 * <p>
 * Input: s = "s"
 * Output: "s"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 100
 * s contains only lower and upper case English letters.
 */
public class _1544MakeTheStringGood {


    public String makeGood(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stk.isEmpty()) {
                stk.add(s.charAt(i));
            } else {
                if (Math.abs(stk.peek() - s.charAt(i)) == 32) {
                    stk.pop();
                } else {
                    stk.add(s.charAt(i));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stk) {
            sb.append(c);
        }

        return sb.toString();
    }

}