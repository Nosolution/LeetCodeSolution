import java.util.Arrays;

/**
 * 1996. The Number of Weak Characters in the Game
 * Medium
 * <p>
 * <p>
 * You are playing a game that contains multiple characters, and each of the characters has two main properties: attack and defense.
 * You are given a 2D integer array properties where properties[i] = [attacki, defensei] represents the properties of the ith character in the game.
 * A character is said to be weak if any other character has both attack and defense levels strictly greater than this character's attack and defense levels.
 * More formally, a character i is said to be weak if there exists another character j where attackj > attacki and defensej > defensei.
 * Return the number of weak characters.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: properties = [[5,5],[6,3],[3,6]]
 * Output: 0
 * Explanation: No character has strictly greater attack and defense than the other.
 * Example 2:
 * <p>
 * Input: properties = [[2,2],[3,3]]
 * Output: 1
 * Explanation: The first character is weak because the second character has a strictly greater attack and defense.
 * Example 3:
 * <p>
 * Input: properties = [[1,5],[10,4],[4,3]]
 * Output: 1
 * Explanation: The third character is weak because the second character has a strictly greater attack and defense.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= properties.length <= 10^5
 * properties[i].length == 2
 * 1 <= attacki, defensei <= 10^5
 */
public class _1996WeakCharacter {


    public int numberOfWeakCharacters(int[][] properties) {
        //攻击降序排序，防御升序排序
        Arrays.sort(properties, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0]);
        int maxDfs = -1, res = 0;
        //后来者攻击一定比前者攻击低，当是weak的时候不更新max defense
        for (int[] property : properties) {
            if (property[1] < maxDfs) res++;
            else maxDfs = property[1];
        }
        return res;
    }


}


