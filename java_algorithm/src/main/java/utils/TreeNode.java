package utils;


import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树节点类
 */
public class TreeNode {

    public int val; // 节点值
    public int height; // 节点高度

    public TreeNode left; // 左子节点引用
    public TreeNode right; // 右子节点引用

    public TreeNode(int x) {
        val = x;
    }
    /**
     * 将列表反序列化为二叉树 递归
     * List<Integer> arr = Arrays.asList(1, 2, 3, 4, 5, 6, 7)
     * i = 0,
     *  i = 2 * 0 + 1 = 1,
     *      i = 2 * 1 + 1 = 3
     *          i = 2 * 3 + 1 = 7 跳出
     *          i = 2 * 3 + 2 = 8 跳出
     *      i = 2 * 1 + 2 = 4
     *          i = 2 * 4 + 1 = 9 跳出
     *          i = 2 * 4 + 2 = 10 跳出
     *
     *  i = 2 * 0 + 2 = 2,
     *      i = 2 * 2 + 1 = 5
     *          i = 2 * 5 + 1 = 11 跳出
     *          i = 2 * 5 + 2 = 12 跳出
     *      i = 2 * 2 + 2 = 6
     *          i = 2 * 6 + 1 = 13 跳出
     *          i = 2 * 6 + 2 = 14 跳出
     */
    private static TreeNode listToTreeDFS(List<Integer> arr, int i) {
        if (i < 0 || i >= arr.size() || arr.get(i) == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr.get(i));
        root.left = listToTreeDFS(arr, 2 * i + 1);
        root.right = listToTreeDFS(arr, 2 * i + 2);
        return root;
    }


    /**
     * 将列表反序列化为二叉树
     */
    public static TreeNode listToTree(List<Integer> arr) {
        return listToTreeDFS(arr, 0);
    }


    /**
     * 将二叉树序列化为列表 递归
     */
    private static void treeToListDFS(TreeNode root, int i, List<Integer> res){
        if (root == null) {
            return;
        }

        while (i >= res.size()) {
            res.add(null);
        }

        res.set(i, root.val);
        treeToListDFS(root.left, 2 * i + 1, res);
        treeToListDFS(root.right, 2 * i + 2, res);
    }

    /**
     * 将二叉树序列化为列表
     */
    public static List<Integer> treeToList(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        treeToListDFS(root, 0, res);
        return res;
    }

}
