import java.util.*;

/**
 * 199. Binary Tree Right Side View
 * Medium
 * <p>
 * Given the root of a binary tree, imagine yourself standing on the right side of it,
 * return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1,null,3]
 * Output: [1,3]
 * <p>
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class _199BinaryTreeRIghtSideView {

    /**
     * 层级遍历，从右往左，没有难度
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        TreeNode node;

        Queue<TreeNode> queue = new LinkedList<>();
        int size;
        queue.add(root);

        while (!queue.isEmpty()) {
            size = queue.size();
            res.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }

        return res;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}



