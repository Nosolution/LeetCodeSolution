import java.util.Arrays;

/**
 * 1042. Flower Planting With No Adjacent
 * Medium
 * <p>
 * <p>
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between garden xi to garden yi.
 * In each garden, you want to plant one of 4 types of flowers.
 * All gardens have at most 3 paths coming into or leaving it.
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Explanation:
 * Gardens 1 and 2 have different types.
 * Gardens 2 and 3 have different types.
 * Gardens 3 and 1 have different types.
 * Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
 * Example 2:
 * <p>
 * Input: n = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * Example 3:
 * <p>
 * Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^4
 * 0 <= paths.length <= 2 * 10^4
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * Every garden has at most 3 paths coming into or leaving it.
 */
public class _1042FlowerPlantingWithNoAdjacent {


    //硬写就过了，搞不懂考的什么
    public int[] gardenNoAdj(int n, int[][] paths) {

        int[][] gardens = new int[n + 1][3];
        for (int i = 1; i < n; i++) {
            gardens[i] = new int[3];
        }

        for (int[] path : paths) {
            fillGardenPath(gardens, path[0], path[1]);
            fillGardenPath(gardens, path[1], path[0]);
        }

        for (int[] adjs : gardens) {
            Arrays.sort(adjs);
        }

        int[] colors = new int[n];

        for (int i = 1; i <= n; i++) {
            if (colors[i - 1] == 0) {
                int[] option = new int[]{1, 1, 1, 1};
                for (int adj : gardens[i]) {
                    if (adj != 0 && colors[adj - 1] != 0) {
                        option[colors[adj - 1] - 1] = 0;
                    }
                }
                for (int o = 0; o < 4; o++) {
                    if (option[o] != 0) {
                        colors[i - 1] = o + 1;
                        break;
                    }
                }
            }
        }


        return colors;

    }


    private void fillGardenPath(int[][] gardens, int g, int t) {
        for (int i = 0; i < 3; i++) {
            if (gardens[g][i] == 0) {
                gardens[g][i] = t;
                break;
            }
        }
    }

}


