import java.util.*;


/**
 * 515. Find Largest Value in Each Tree Row
 * Medium
 * <p>
 * Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,3,2,5,3,null,9]
 * Output: [1,3,9]
 * Example 2:
 * <p>
 * Input: root = [1,2,3]
 * Output: [1,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree will be in the range [0, 10^4].
 * -2^31 <= Node.val <= 2^31 - 1
 */
public class _515FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        helper(root, res, 0);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res, int d) {
        if (root == null) {
            return;
        }
        //expand list size
        if (d == res.size()) {
            res.add(root.val);
        } else {
            //or set value
            res.set(d, Math.max(res.get(d), root.val));
        }
        helper(root.left, res, d + 1);
        helper(root.right, res, d + 1);
    }

//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> res = new LinkedList<>();
//        if (root == null) {
//            return res;
//        } else {
//            res.add(root.val);
//        }
//
//        Iterator<Integer> left = largestValues(root.left).iterator();
//        Iterator<Integer> right = largestValues(root.right).iterator();
//
//        while (left.hasNext() && right.hasNext()) {
//            res.add(Math.max(left.next(), right.next()));
//        }
//
//        while (left.hasNext()) {
//            res.add(left.next());
//        }
//
//        while (right.hasNext()) {
//            res.add(right.next());
//        }
//
//
//        return res;
//
//    }


//    public List<Integer> largestValues(TreeNode root) {
//        List<Integer> res = new LinkedList<>();
//        Queue<TreeNode> queue = new LinkedList<>();
//        if (root != null) {
//            queue.add(root);
//        } else {
//            return res;
//        }
//        TreeNode node;
//
//        while (!queue.isEmpty()) {
//            int sz = queue.size();
//            int max = Integer.MIN_VALUE;
//            for (int i = 0; i < sz; i++) {
//                node = queue.poll();
//                max = Math.max(node.val, max);
//                if (node.left != null) {
//                    queue.add(node.left);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                }
//            }
//            res.add(max);
//        }
//
//        return res;
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




