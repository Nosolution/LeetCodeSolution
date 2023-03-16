/**
 * 1138. Alphabet Board Path
 * Medium
 * <p>
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * <p>
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
 * <p>
 * We may make the following moves:
 * <p>
 * 'U' moves our position up one row, if the position exists on the board;
 * 'D' moves our position down one row, if the position exists on the board;
 * 'L' moves our position left one column, if the position exists on the board;
 * 'R' moves our position right one column, if the position exists on the board;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * (Here, the only positions that exist on the board are positions with letters on them.)
 * <p>
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return any path that does so.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = "leet"
 * Output: "DDR!UURRR!!DDD!"
 * Example 2:
 * <p>
 * Input: target = "code"
 * Output: "RR!DDRR!UUL!R!"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= target.length <= 100
 * target consists only of English lowercase letters.
 */
public class _1138AlphabetBoardPath {

    int[][] cors = new int[26][];

    //不用BFS，把z作为u的特殊情况来处理，共四种情况
    public String alphabetBoardPath(String target) {
        StringBuilder res = new StringBuilder();
        int[] cur = new int[]{0, 0}, coord;
        char c = '0';
        int d;
        for (char letter : target.toCharArray()) {
            if (letter == 'z') {
                if (c == 'z') {
                    res.append('!');
                    continue;
                } else {
                    coord = cor('u');
                }
            } else {
                if (c == 'z') {
                    res.append('U');
                    cur = cor('u');
                }
                coord = cor(letter);
            }

            d = coord[0] - cur[0];
            while (d > 0) {
                res.append('D');
                d--;
            }
            while (d < 0) {
                res.append('U');
                d++;
            }

            d = coord[1] - cur[1];
            while (d > 0) {
                res.append('R');
                d--;
            }
            while (d < 0) {
                res.append('L');
                d++;
            }

            if (letter == 'z') {
                res.append('D');
            }

            res.append('!');
            c = letter;
            cur = coord;

        }


        return res.toString();
    }

    private int[] cor(char c) {
        int v = c - 'a';

        if (cors[v] == null) {
            int[] res = new int[]{0, 0};
            res[0] = v / 5;
            res[1] = v % 5;
            cors[v] = res;
        }

        return cors[v];
    }

}




