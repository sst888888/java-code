package com.example.ob.ex2;


public class Test {

    public static void main(String[] args) {
        Subject43D subject43D = new Subject43D();
        Subject4SSQ subject4SSQ = new Subject4SSQ();

        Observer3 observer3 = new Observer3();
        observer3.registerSubject(subject43D);
        observer3.registerSubject(subject4SSQ);

        subject43D.setMsg("666666");
        subject4SSQ.setMsg("88888888");
    }
}