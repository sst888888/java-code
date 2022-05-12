package org.code.example.leetcode.algorithm;

import java.util.Scanner;

/**
 * @ClassName Triangle
 * @Description 三角形
 * @Author Chaiphat
 * @Date 2020/1/25 20:59
 * @Version 1.0
 **/
public class Triangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入行数：");
        int rows = sc.nextInt();

        for (int row = 1;row<=rows;row++) {
            // 空格：行数 - 行号
            for (int col = 1; col <= rows - row; col++) {
                System.out.print(" ");
            }

            for (int col = 1; col <= row * 2 - 1; col++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }


}
