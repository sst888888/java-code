package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName lc102
 * @Description Binary Tree Level Order Traversal
 * @Author Chaiphat
 * @Date 2020/2/8 21:02
 * @Version 1.0
 **/
public class lc102 {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root){
        Queue<TreeNode> qu = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(null == root){
            return res;
        }

        qu.add(root);
        while(!qu.isEmpty()){
            int size = qu.size();
            List<Integer> temp = new ArrayList<>();
            while(size > 0){
                TreeNode tn = qu.remove();
                if(null != tn){
                    if(tn.left!=null){
                        qu.add(tn.left);
                    }
                    if(tn.right!=null){
                        qu.add(tn.right);
                    }
                }
                temp.add(tn.val);
                size--;
            }
            res.add(temp);
        }
        return res;
    }

}
