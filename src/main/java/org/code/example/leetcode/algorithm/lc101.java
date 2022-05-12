package org.code.example.leetcode.algorithm;

import java.util.Stack;

/**
 * @ClassName lc101
 * @Description 判断二叉树是否对称
 * 注意：递归方法 left.left与right.right, left.right与right.left 进行比较
 * @Author Chaiphat
 * @Date 2020/2/8 20:14
 * @Version 1.0
 **/
public class lc101 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public boolean isSymmetric(TreeNode root){
        if(root == null) {
            return Boolean.TRUE;
        }

        Stack<TreeNode> s = new Stack<>();
        s.push(root.left);
        s.push(root.right);
        while(!s.isEmpty()){
            TreeNode tn1 = s.pop(),tn2 = s.pop();
            if(null == tn1 || null == tn2){
                if(tn1 == tn2){
                    continue;
                }
                else{
                    return Boolean.FALSE;
                }
            }

            if(tn1.val != tn2.val){
                return Boolean.FALSE;
            }

            s.push(tn1.left);
            s.push(tn2.right);
            s.push(tn1.right);
            s.push(tn2.left);
        }
        return Boolean.TRUE;
    }

    public boolean isSymmetric2(TreeNode root){
        return root==null || func(root.left,root.right);
    }

    public boolean func(TreeNode left, TreeNode right){
        if(null == left || null == right){
            return left == right;
        }

        return (left.val==right.val)&&func(left.left,right.right)&&func(left.right,right.left);
    }

}
