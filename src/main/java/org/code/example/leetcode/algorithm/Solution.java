package org.code.example.leetcode.algorithm;

import java.util.*;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author Chaiphat
 * @Date 2020/1/28 16:09
 * @Version 1.0
 **/
public class Solution {

    public List<String> letterCombinations(String digits){
        if(digits.equals("")){
            return new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        String []arr = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        dfs(list,arr,new StringBuilder(),0,digits);
        return list;
    }

    public void dfs(List<String> list,String []arr,StringBuilder sb,int cur,String digits){
        if(cur == digits.length()){
            list.add(sb.toString());
            return;
        }

        int num = Integer.parseInt(digits.substring(cur, cur + 1));
        for(int i = 0; i < arr[num].length(); i ++){
            sb.append(arr[num].charAt(i));
            dfs(list,arr,sb,cur + 1,digits);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("name1","name1");
        map.put("name2","name2");
        map.put("name3","name3");
        map.put("name4","name4");
        map.put("name5","name5");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }



        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name1","name1");
        hashMap.put("name2","name2");
        hashMap.put("name3","name3");
        hashMap.put("name4","name4");
        hashMap.put("name5","name5");
      //  hashMap.forEach((key,value)->  System.out.println("key=" + key + " value=" + value));

        Iterator<Map.Entry<String, String>> entryIterator = hashMap.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String, String> next = entryIterator.next();
            System.out.println("key=" + next.getKey() + " value=" + next.getValue());
        }


        Solution solution = new Solution();
       // solution.letterCombinations("23");
    }

}
