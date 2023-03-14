import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 144. Binary Tree Preorder Traversal
 * Easy
 * <p>
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class _144BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preorder(root, res);

        return res;
    }

    private void preorder(TreeNode node, List<Integer> track) {
        if (node == null) {
            return;
        }

        track.add(node.val);
        preorder(node.left, track);
        preorder(node.right, track);
    }

//    private void preorder(TreeNode node, List<Integer> track) {
//        Stack<TreeNode> stk = new Stack<>();
//
//        while (node != null || !stk.isEmpty()) {
//            while (node != null) {
//                track.add(node.val);
//                stk.add(node);
//                node = node.left;
//            }
//
//            if (!stk.isEmpty()) {
//                node = stk.pop();
//                node = node.right;
//            }
//        }
//    }


    static class TreeNode {
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
}
