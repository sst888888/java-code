package com.example.state.ex1;



public abstract class AbstractState {

    protected ScoreContext hj;  //环境
    protected String stateName; //状态名
    protected int score; //分数
    public abstract void checkState(); //检查当前状态
    public void addScore(int x) {
        score += x;
        System.out.print("加上：" + x + "分，当前分数：" + score);
        checkState();
        System.out.println("分，当前状态：" + hj.getState().stateName);
    }

}
