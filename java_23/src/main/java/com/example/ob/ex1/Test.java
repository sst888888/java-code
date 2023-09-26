package com.example.ob.ex1;

public class Test {

    public static void main(String[] args) {
        Subject3D subject3D = new Subject3D();

        Observer1 observer1 = new Observer1(subject3D);
        Observer2 observer2 = new Observer2(subject3D);

        subject3D.setMsg("111");
        subject3D.setMsg("888");
    }
}
