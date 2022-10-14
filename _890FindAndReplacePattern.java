import java.util.*;

/**
 * 890. Find and Replace Pattern
 * Medium
 * <p>
 * <p>
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern.
 * You may return the answer in any order.
 * <p>
 * A word matches the pattern if there exists a permutation of letters p so that after replacing
 * every letter x in the pattern with p(x), we get the desired word.
 * <p>
 * Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
 * and no two letters map to the same letter.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 * Example 2:
 * <p>
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 */
public class _890FindAndReplacePattern {

    //使用双向字典来记录匹配情况
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> res = new ArrayList<>();
        int[] spDict = new int[26];
        int[] psDict = new int[26];
        for (String word : words) {
            if (word.length() != pattern.length())
                continue;

            Arrays.fill(spDict, -1);
            Arrays.fill(psDict, -1);
            int i = 0;
            for (; i < word.length(); i++) {
                char s = word.charAt(i), p = pattern.charAt(i);
                if (spDict[s - 'a'] == -1 && psDict[p - 'a'] == -1) {
                    spDict[s - 'a'] = p;
                    psDict[p - 'a'] = s;
                } else if (spDict[s - 'a'] != p || psDict[p - 'a'] != s) {
                    break;
                }
            }

            if (i == word.length()) {
                res.add(word);
            }
        }

        return res;
    }


}