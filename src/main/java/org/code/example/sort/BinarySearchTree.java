package org.code.example.sort;

/**
 * @ClassName BinarySearchTree
 * @Description 二叉树
 * @Author Chaiphat
 * @Date 2020/3/9 17:42
 * @Version 1.0
 **/
public class BinarySearchTree {

    private Node tree;

    public Node find(int data){
        Node p = tree;
        while(p != null){
            if(data < p.data){
                p = p.left;
            }else if (data > p.data){
                p = p.right;
            }else {
                return p;
            }
        }
        return null;
    }

    public void insert(int data){
        if(tree == null){
            tree = new Node(data);
            return;
        }

        Node p = tree;
        while(p != null){
            if(data > p.data){
                // 右节点
                if(p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }else {
                // 左节点
                if(p.left == null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }
        }

    }


    public void delete(int data){
        // p指向要删除的节点，初始化指向根节点
        Node p = tree;
        // pp记录的是p的父节点
        Node pp = null;
        while(p != null && p.data != data){
            pp = p;
            if(data > p.data){
                p = p.right;
            }else {
                p = p.left;
            }
        }

        // 没有找到
        if(p == null){
            return;
        }

        // 要删除的节点有2个子节点
        // 查找右子树中最小节点
        if(p.left != null && p.right != null){
            Node minP = p.right;
            // minPP 表示 minP 的父节点
            Node minPP = p;
            while(minP.left != null){
                minPP = minP;
                minP = minP.left;
            }
            // 将minP的数据替换到p中
            p.data = minP.data;
            // 下面就变成删除minP了
            p = minP;
            pp = minPP;
        }

        // 删除节点是叶子节点或者只有一个子节点
        // p的子节点
        Node child;
        if(p.left != null){
            child = p.left;
        }else if (p.right != null){
            child = p.right;
        }else {
            child = null;
        }

        if(pp == null){
            // 删除的是根节点
            tree = child;
        }else if (pp.left == p){
            pp.left = child;
        }else {
            pp.right = child;
        }
    }


    public Node findMin(){
        if(tree == null){
            return null;
        }
        Node p = tree;
        while (p.left != null){
            p = p.left;
        }
        return p;
    }

    public Node findMax(){
        if(tree == null){
            return null;
        }
        Node p = tree;
        while (p.right != null){
            p = p.right;
        }
        return p;
    }


    public static class Node{
        private int data;
        private Node left;
        private Node right;
        public Node(int data){
            this.data = data;
        }
    }
}
