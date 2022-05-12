package org.code.example.leetcode.algorithm;

import com.sun.java.accessibility.util.TopLevelWindowListener;

import java.util.ArrayDeque;
import java.util.List;

/**
 * @ClassName lc127
 * @Description 给定开始字符串，结束字符串，和一个字符串数组，每次替换字符串中的一个字符，问最少几个步骤变为终止字符串
 * @Author Chaiphat
 * @Date 2020/2/15 21:11
 * @Version 1.0
 **/
public class lc127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList){
        if(!wordList.contains(endWord)){
            return 0;
        }
        ArrayDeque<String> qu = new ArrayDeque<>();
        qu.add(beginWord);
        int level = 2;
        while(!qu.isEmpty()){
            int size = qu.size();
            for(int i = 0; i < size; i++){
                char[] curr_str = qu.remove().toCharArray();
                System.out.println(String.valueOf(curr_str));
                for(int j = 0; j < curr_str.length; j++){
                    char ch = curr_str[j];
                    for(char k = 'a';k <= 'z';k++){
                        curr_str[j] = k;
                        if(String.valueOf(curr_str).equals(endWord)){
                            return level;
                        }
                        if(wordList.contains(String.valueOf(curr_str))){
                            wordList.remove(String.valueOf(curr_str));
                            qu.add(String.valueOf(curr_str));
                        }
                    }
                    curr_str[j] = ch;
                }
            }
            level++;
        }
        return 0;
    }

}
