package org.code.example.leetcode.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SolutionIP
 * @Description 给定一个只包含数字的字符串，复原它并返回所有可能的IP地址格式。
 * @Author Chaiphat
 * @Date 2020/1/28 16:52
 * @Version 1.0
 **/
public class SolutionIP {

    public static void main(String[] args) {
        SolutionIP solutionIP = new SolutionIP();
        List<String> result = solutionIP.restoreIpAddress("25525511135");
        result.forEach(item-> System.out.println(item));
    }

    public List<String> restoreIpAddress(String s){
        List<String> address = new ArrayList<>();
        StringBuilder tempAddress = new StringBuilder();
        doRestore(0, tempAddress, address, s);
        return address;
    }

    /**
     *
     * @param k ip的段数 每段的长度不超过3
     * @param tempAddress
     * @param address
     * @param s 总的长度
     */
    private void doRestore(int k, StringBuilder tempAddress, List<String> address, String s){
        if(k == 4 || s.length() == 0){
            if(k == 4 && s.length() == 0){
                address.add(tempAddress.toString());
            }
            return;
        }

        // i从0到2进行遍历
        for(int i = 0; i < s.length() && i <= 2; i ++){
            if(s.charAt(0) == '0' && i != 0){
                break;
            }
            String part = s.substring(0, i + 1);
            if(Integer.valueOf(part) <= 255){
                if(tempAddress.length() != 0){
                    part = "." + part;
                }
                tempAddress.append(part);
                doRestore(k + 1, tempAddress, address, s.substring(i + 1));
                tempAddress.delete(tempAddress.length() - part.length(), tempAddress.length());
            }
        }
    }

}
