package com.example.state.ex1;

import com.example.state.ex1.AbstractState;

// @link https://www.xinbaoku.com/archive/ErcLaDsq.html
public class ScoreContext {

    private AbstractState state;
    ScoreContext() {
        state = new LowState(this);
    }
    public void setState(AbstractState state) {
        this.state = state;
    }
    public AbstractState getState() {
        return state;
    }
    public void add(int score) {
        state.addScore(score);
    }

}
