package org.code.example.sort;

import java.util.Collections;

/**
 * @ClassName Trie
 * @Description 树
 * @Author Chaiphat
 * @Date 2020/3/12 19:28
 * @Version 1.0
 **/
public class Trie {

    /**
     * 存储无意义字符
     */
    private TrieNode root = new TrieNode('/');

    /**
     * 往 Trie 树中插入一个字符串
     * @param text
     */
    public void insert(char[] text){
        TrieNode p = root;
        for(int i = 0; i < text.length; ++i){
            int index = text[i] - 'a';
            /**
             * 当子节点不存的时候，创建子节点。
             */
            if(p.children[index] == null){
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            /**
             * p 指向子节点
             */
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    /**
     * 在 Trie中查找一个字符
     * @param pattern
     * @return
     */
    public boolean find(char[] pattern){
        TrieNode p = root;
        for(int i = 0; i < pattern.length; ++i){
            int index = pattern[i] - 'a';
            if(p.children[index] == null){
                // 不存在pattern
                return false;
            }
            p = p.children[index];
        }

        if(p.isEndingChar == false){
            return false;
        }else {
            return true;
        }
    }

    class TrieNode{
        public char data;
        public TrieNode[] children = new TrieNode[26];
        public boolean isEndingChar = false;
        public TrieNode(char data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        int size = Collections.emptyList().size();
        System.out.println(size);
    }


}
