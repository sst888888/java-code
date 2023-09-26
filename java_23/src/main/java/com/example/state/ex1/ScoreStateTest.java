package com.example.state.ex1;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScoreStateTest {

    public static void main(String[] args) {
        ScoreContext account = new ScoreContext();
        log.info("学生成绩状态测试：");
        account.add(30);
        account.add(40);
        account.add(25);
        account.add(-15);
        account.add(-25);
    }

}
