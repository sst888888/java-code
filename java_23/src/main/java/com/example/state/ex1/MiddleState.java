package com.example.state.ex1;


public class MiddleState extends AbstractState {
    public MiddleState(AbstractState state) {
        super.hj = state.hj;
        super.stateName = "中等";
        super.score = state.score;
    }
    public void checkState() {
        if (score < 60) {
            hj.setState(new LowState(this));
        } else if (score >= 90) {
            hj.setState(new HighState(this));
        }
    }
}
