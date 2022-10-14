/**
 * 1535. Find the Winner of an Array Game
 * Medium
 * <p>
 * Given an integer array arr of distinct integers and an integer k.
 * <p>
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]).
 * In each round of the game, we compare arr[0] with arr[1], the larger integer wins and remains at position 0,
 * and the smaller integer moves to the end of the array. The game ends when an integer wins k consecutive rounds.
 * <p>
 * Return the integer which will win the game.
 * <p>
 * It is guaranteed that there will be a winner of the game.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,1,3,5,4,6,7], k = 2
 * Output: 5
 * Explanation: Let's see the rounds of the game:
 * Round |       arr       | winner | win_count
 * 1   | [2,1,3,5,4,6,7] | 2      | 1
 * 2   | [2,3,5,4,6,7,1] | 3      | 1
 * 3   | [3,5,4,6,7,1,2] | 5      | 1
 * 4   | [5,4,6,7,1,2,3] | 5      | 2
 * So we can see that 4 rounds will be played and 5 is the winner because it wins 2 consecutive games.
 * Example 2:
 * <p>
 * Input: arr = [3,2,1], k = 10
 * Output: 3
 * Explanation: 3 will win the first 10 rounds consecutively.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= arr.length <= 10^5
 * 1 <= arr[i] <= 10^6
 * arr contains distinct integers.
 * 1 <= k <= 10^9
 */
public class _1535FindTheWinnerOfArrayGame {

    /**
     * 经过模拟可以发现，本质上是选择数组前两个元素比大小，小者再排入数组。对于一个数来说，比其小的数一定先进入数组，由此本质是一个逐步选中较大数字的过程。
     * 只要在过程中某个数维持在胜利位置次数够多或者找到最大的数就可以停止比赛了。
     * 对数组进行一次遍历，遍历过程中统计较大数的胜利次数，到达k则终止，否则找到数组最后维持的较大数一定是最大数，后续结果不变，结束循环。
     */
    public int getWinner(int[] arr, int k) {
        int winCt = 0, champi = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[champi]) {
                champi = i;
                winCt = 0;
            }
            winCt += 1;
            if (winCt == k)
                break;
        }

        return arr[champi];
    }


}