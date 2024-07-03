package chapter.tree;

import utils.PrintUtil;
import utils.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 深度优先
 */
public class BinaryTreeDfs {
    // 初始化列表 用于存储遍历序列
    static ArrayList<Integer> list = new ArrayList<>();

    /**
     * 前序遍历
     */
    static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // 访问优先级：根节点 -> 左子树 -> 右子树
        list.add(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 中序遍历
     */
    static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // 访问优先级：左子树 -> 根节点 -> 右子树
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }

    /**
     * 后序遍历
     */
    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        // 访问优先级：左子树 -> 右子树 -> 根节点
        postOrder(root.left);
        postOrder(root.right);
        list.add(root.val);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);


        /* 前序遍历 */
        list.clear();
        preOrder(root);
        System.out.println("\n前序遍历的节点打印序列 = " + list);

        /* 中序遍历 */
        list.clear();
        inOrder(root);
        System.out.println("\n中序遍历的节点打印序列 = " + list);

        /* 后序遍历 */
        list.clear();
        postOrder(root);
        System.out.println("\n后序遍历的节点打印序列 = " + list);

    }
}
