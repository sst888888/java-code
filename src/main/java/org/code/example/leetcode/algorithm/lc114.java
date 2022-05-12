package org.code.example.leetcode.algorithm;

/**
 * @ClassName lc114
 * @Description 二叉树转链表
 * @Author Chaiphat
 * @Date 2020/2/15 20:49
 * @Version 1.0
 **/
public class lc114 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public void flatten(TreeNode root){
        if(null == root){
            return;
        }

        flatten(root.left);
        flatten(root.right);
        TreeNode origin_r = root.right;
        root.right = root.left;
        TreeNode right_leaf = root;
        while(right_leaf.right != null){
            right_leaf = right_leaf.right;
        }
        right_leaf.right = origin_r;
        root.left = null;
    }


}
