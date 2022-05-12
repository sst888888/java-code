package org.code.example.sort;

import java.util.Random;

/**
 * @ClassName SkipList
 * @Description 跳表
 * @Author Chaiphat
 * @Date 2020/3/7 14:38
 * @Version 1.0
 **/
public class SkipList {

    /**
     * 索引最大层次，包含原生链表那一层。
     */
    private static final int MAX_LEVEL = 16;

    /**
     * 当前索引层数
     */
    private int levelCount = 1;

    /**
     * 跳表头节点
     * 类似链表里的头节点，哨兵节点，本身不存储数据，下一跳指向各层索引的头节点。
     */
    private Node head;


    /**
     * 用于生成随机数，避免链表中节点过多，导致性能下降。
     */
    private Random r;

    public SkipList(){
        head = new Node();
        r = new Random();

    }


    /**
     * 生成随机数 level = [1,MAX_LEVEL]
     * 将节点添加到第 0 层到第 level-1 层索引中
     * @return
     */
    private int randomLevel(){
        int level = 1;
        for(int i = 1; i < MAX_LEVEL; i++){
            if(r.nextInt() % 2 == 1){
                level++;
            }
        }
        return level;
    }

    /**
     * 往链表插入一个数据，数据从小到大排序。
     * @param value
     */
    public void insert(int value){
        // 生成随机数
        int level = randomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;

        Node[] update = new Node[level];
        for(int i = level - 1; i >= 0; i--){
            update[i] = head;
        }

        Node p = head;
        for(int i = level -1; i >= 0; i--){
            // 找到第i层索引的插入位置，将插入位置前面的节点保存到 update 数组
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        // 更新当前索引总层数
        if(levelCount < level){
            levelCount = level;
        }

    }

    /**
     * 删除跳表数据
     * @param value
     */
    public void delete(int value){
        Node[] update = new Node[levelCount];
        Node p = head;
        for(int i = levelCount - 1; i >= 0; i--){
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
            update[i] = p;
        }

        if(p.forwards[0] != null && p.forwards[0].data == value){
            for(int i = levelCount - 1; i >= 0; i--){
                // 判断第 i 层索引是否有要删除的节点
                if(update[i].forwards[i] != null && update[i].forwards[i].data == value){
                    update[i].forwards[i] = update[i].forwards[i].forwards[i];
                }
            }
        }
    }


    public Node find(int value){
        Node p = head;
        // 从上层开始查找，如果下一跳节点的值大于等于要查找的值，切换到下一层继续查找，直到第 0 层
        for(int i = levelCount - 1; i >= 0; i--){
            while(p.forwards[i] != null && p.forwards[i].data < value){
                p = p.forwards[i];
            }
        }

        // 第 0 层存储了所有节点，如果找到要查找的值，返回该节点，否则返回null
        if(p.forwards[0] != null && p.forwards[0].data == value){
            return p.forwards[0];
        }

        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SkipList Level Count = ").append(levelCount).append("\n");
        for(int i = levelCount - 1; i >= 0; i--){
            sb.append("level ").append(i).append(" --> ");
            for(Node p = head; p.forwards[i] != null; p = p.forwards[i]){
                sb.append(p.forwards[i].data);
                if(p.forwards[i].forwards[i] != null){
                    sb.append(",").append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * 跳表结点
     */
    public class Node{

        /**
         * 节点存储的整数值
         */
        private int data = -1;

        /**
         * 当前索引总层数
         * 索引从 0 开始计数，到 maxLevel - 1 为止
         * 第 0 层为原始链表，从下往上依次建立索引，最上层为 maxLevel - 1 层索引
         */
        private int maxLevel = 0;

        /**
         * 保存当前结点的所有下一跳结点
         * forwards[i] 表示当前结点在第 i 层索引的下一跳结点，i in [0,maxlevel - 1]
         */
        private Node forwards[] = new Node[MAX_LEVEL];

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("SkipList Node ").append(data);
            sb.append(", maxLevel = ").append(maxLevel).append("\n");
            for(int i = maxLevel - 1; i >= 0; i--){
                sb.append("level ").append(i).append(" --> ");
                if(forwards[i] == null){
                    sb.append("null");
                }else {
                    sb.append(forwards[i].data);
                }
                sb.append("\n");
            }
            return sb.toString();
        }

    }




}
