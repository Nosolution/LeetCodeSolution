import java.util.*;


/**
 * 1003. Check If Word Is Valid After Substitutions
 * Medium
 * <p>
 * <p>
 * Given a string s, determine if it is valid.
 * <p>
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
 * <p>
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 * Example 2:
 * <p>
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 * Example 3:
 * <p>
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2 * 104
 * s consists of letters 'a', 'b', and 'c'
 */
public class _1003CheckIfWordIsValidAfterSubstitutions {


    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int i = 0, size = 0;
        char ch;
        while (i < s.length()) {
            ch = s.charAt(i);
            if (ch == 'c') {
                if (size >= 2 && stack.get(size - 1) == 'b' && stack.get(size - 2) == 'a') {
                    stack.removeLast();
                    stack.removeLast();
                    size -= 2;
                } else {
                    stack.add(ch);
                    size++;
                }
            } else {
                stack.add(ch);
                size++;
            }
            i++;
        }

        return stack.isEmpty();
    }

    //对于任何一个被分开的abc，从左到右搜索时，一定会首先遇到完整的abc，由此可以通过栈来处理
    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == 'c') {
                if (stack.isEmpty() || stack.pop() != 'b') return false;
                if (stack.isEmpty() || stack.pop() != 'a') return false;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }



}
