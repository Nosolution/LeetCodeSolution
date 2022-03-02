import java.util.*;

/**
 * 1032. Stream of Characters
 * Hard
 * <p>
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.
 * <p>
 * For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z',
 * your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.
 * <p>
 * Implement the StreamChecker class:
 * StreamChecker(String[] words) Initializes the object with the strings array words.
 * boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * Output
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 * <p>
 * Explanation
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // return False
 * streamChecker.query("b"); // return False
 * streamChecker.query("c"); // return False
 * streamChecker.query("d"); // return True, because 'cd' is in the wordlist
 * streamChecker.query("e"); // return False
 * streamChecker.query("f"); // return True, because 'f' is in the wordlist
 * streamChecker.query("g"); // return False
 * streamChecker.query("h"); // return False
 * streamChecker.query("i"); // return False
 * streamChecker.query("j"); // return False
 * streamChecker.query("k"); // return False
 * streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * words[i] consists of lowercase English letters.
 * letter is a lowercase English letter.
 * At most 4 * 10^4 calls will be made to query.
 */
public class _1032StreamOfCharacters {

    private TrieNode root;
    private Queue<TrieNode> nodeQueue;

    public _1032StreamOfCharacters(String[] words) {
        root = new TrieNode();
        nodeQueue = new LinkedList<>();
        for (String word : words) {
            insert(word);
        }
    }

    /**
     * 使用队列保存状态（节点）的方式
     * 实际将Stream倒置，每次从头查询，效率更高。
     */
    public boolean query(char letter) {
        boolean res = false;
        TrieNode node, next;
        nodeQueue.add(root);
        int size = nodeQueue.size();
        for (int i = 0; i < size; i++) {
            node = nodeQueue.poll();
            next = node.next(letter);
            if (next != null) {
                if (next.isEnd) {
                    res = true;
                }
                nodeQueue.add(next);
            }
        }
        return res;
    }

    private void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];
        }
        cur.isEnd = true;
    }

    /**
     * 用字典树来合并多个查询序列
     */
    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }

        public TrieNode next(char c) {
            return children[c - 'a'];
        }
    }

}


