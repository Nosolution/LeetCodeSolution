import java.util.Deque;
import java.util.LinkedList;

/**
 * 71. Simplify Path
 * Medium
 * <p>
 * Given a string path, which is an absolute path (starting with a slash '/') to a file or directory in a Unix-style file system, convert it to the simplified canonical path.
 * In a Unix-style file system, a period '.' refers to the current directory, a double period '..' refers to the directory up a level, and any multiple consecutive slashes (i.e. '//') are treated as a single slash '/'.
 * For this problem, any other format of periods such as '...' are treated as file/directory names.
 * <p>
 * The canonical path should have the following format:
 * - The path starts with a single slash '/'.
 * - Any two directories are separated by a single slash '/'.
 * - The path does not end with a trailing '/'.
 * - The path only contains the directories on the path from the root directory to the target file or directory (i.e., no period '.' or double period '..')
 * <p>
 * Return the simplified canonical path.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: path = "/home/"
 * Output: "/home"
 * Explanation: Note that there is no trailing slash after the last directory name.
 * <p>
 * Example 2:
 * <p>
 * Input: path = "/../"
 * Output: "/"
 * Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.
 * <p>
 * Example 3:
 * <p>
 * Input: path = "/home//foo/"
 * Output: "/home/foo"
 * Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.
 * <p>
 * Example 4:
 * <p>
 * Input: path = "/a/./b/../../c/"
 * Output: "/c"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= path.length <= 3000
 * path consists of English letters, digits, period '.', slash '/' or '_'.
 * path is a valid absolute Unix path.
 */
public class _71SimplifyPath {

    /**
     * 迭代寻找'/'再分类讨论就行了，用正则表达式匹配会很慢
     */
    public String simplifyPath(String path) {
        int idx = 0;
        int next = 0;
        String dir;

        Deque<String> deque = new LinkedList<>();

        while (idx < path.length()) {
            next = path.indexOf('/', idx + 1);
            //adapt to boundary condition
            if (next == -1) {
                next = path.length();
            }
            dir = path.substring(idx, next);


            if (next - idx > 1) {
                if (dir.equals("/..")) {
                    if (!deque.isEmpty()) {
                        deque.removeLast();
                    }
                } else if (!dir.equals("/.")) {
                    deque.addLast(dir);
                }
            }
            idx = next;
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}
