package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName lc118
 * @Description TODO
 * @Author Chaiphat
 * @Date 2020/2/15 20:57
 * @Version 1.0
 **/
public class lc118 {

    public List<List<Integer>> generate(int numRows){
        List<List<Integer>> res = new ArrayList<>();
        if(numRows == 0){
            return res;
        }
        List<Integer> ls = new ArrayList<>();
        ls.add(1);
        res.add(ls);
        numRows--;
        if(numRows == 0){
            return res;
        }
        while(numRows > 0){
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int i = 0; i < ls.size() - 1; i++){
                temp.add(ls.get(i)+ls.get(i+1));
            }
            temp.add(1);
            res.add(temp);
            ls = temp;
            numRows--;
        }
        return  res;
    }

}
