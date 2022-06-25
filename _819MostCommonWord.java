import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 819. Most Common word
 * Easy
 * <p>
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 * <p>
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * Output: "ball"
 * Explanation:
 * "hit" occurs 3 times, but it is a banned word.
 * "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 * Note that words in the paragraph are not case sensitive,
 * that punctuation is ignored (even if adjacent to words, such as "ball,"),
 * and that "hit" isn't the answer even though it occurs more because it is banned.
 * <p>
 * Example 2:
 * <p>
 * Input: paragraph = "a.", banned = []
 * Output: "a"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= paragraph.length <= 1000
 * paragraph consists of English letters, space ' ', or one of the symbols: "!?',;.".
 * 0 <= banned.length <= 100
 * 1 <= banned[i].length <= 10
 * banned[i] consists of only lowercase English letters.
 */
public class _819MostCommonWord {

    /**
     * Hashmap
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        String[] words = paragraph.split("\\W+");
        for (String word : words) {
            if (word.length() == 0)
                continue;
            word = word.toLowerCase();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        Set<String> set = new HashSet<>();
        for (String ban : banned) {
            set.add(ban.toLowerCase());
        }

        String res = null;
        int maxCt = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!set.contains(entry.getKey()) && entry.getValue() > maxCt) {
                res = entry.getKey();
                maxCt = entry.getValue();
            }
        }

        return res;
    }

}