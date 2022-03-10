import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1462. Course Schedule IV
 * Medium
 * <p>
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [a_i, b_i] indicates that you must take course a_i first if you want to take course b_i.
 * <p>
 * For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
 * Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.
 * <p>
 * You are also given an array queries where queries[j] = [u_j, v_j]. For the jth query, you should answer whether course u_j is a prerequisite of course v_j or not.
 * <p>
 * Return a boolean array answer, where answer[j] is the answer to the jth query.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
 * Output: [false,true]
 * Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
 * Course 0 is not a prerequisite of course 1, but the opposite is true.
 * <p>
 * Example 2:
 * <p>
 * Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
 * Output: [false,false]
 * Explanation: There are no prerequisites, and each course is independent.
 * <p>
 * Example 3:
 * <p>
 * Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
 * Output: [true,true]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= numCourses <= 100
 * 0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
 * prerequisites[i].length == 2
 * 0 <= a_i, b_i <= n - 1
 * a_i != b_i
 * All the pairs [a_i, b_i] are unique.
 * The prerequisites graph has no cycles.
 * 1 <= queries.length <= 10^4
 * 0 <= u_i, v_i <= n - 1
 * u_i != v_i
 */
public class _1462CourseScheduleIV {


    /**
     * 可达性问题，用可达矩阵可解，对于i->j，需更新m[i][j], m[i][q] for all q st. j->q，以及m[p][q]for all p st. p->。
     * j的q列表可以取巧记录下来。
     */
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Boolean> res = new LinkedList<>();
        int[][] reachable = new int[numCourses][numCourses];
        for (int i = 0; i < numCourses; i++) {
            reachable[i] = new int[numCourses];
        }

        Queue<Integer> queue = new LinkedList<>();
        int i, j;
        for (int[] prerequisite : prerequisites) {
            queue.clear();
            i = prerequisite[0];
            j = prerequisite[1];
            if (reachable[i][j] == 0) {
                reachable[i][j] = 1;
                for (int k = 0; k < numCourses; k++) {
                    if (reachable[j][k] == 1) {
                        reachable[i][k] = 1;
                        queue.add(k);
                    }
                }

                for (int p = 0; p < numCourses; p++) {
                    if (reachable[p][i] == 1) {
                        reachable[p][j] = 1;
                        for (int q = 0; q < queue.size(); q++) {
                            reachable[p][queue.peek()] = 1;
                            queue.add(queue.poll());
                        }
                    }
                }
            }
        }

        for (int[] query : queries) {
            i = query[0];
            j = query[1];
            res.add(reachable[i][j] == 1);
        }

        return res;
    }


}
