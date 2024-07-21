package com.example.command;

/**
 * 调用者，皇帝
 */
public class Invoker {
    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }
    // 本次需要执行的命令
    public void action() {
        command.execute();
    }

    // 测试demo
    public static void main(String[] args) {
        // 实例化一个公公 接收者
        Receiver receiver =new Receiver();
        // 公公 当前能有接收到的几种命令
        Command commandOne = new ConcreteCommandOne(receiver);
        Command commandTwo = new ConcreteCommandTwo(receiver);

        // 皇帝 发号命令 触发执行方法
        Invoker invoker =new Invoker(commandOne);
        invoker.action();
        // result: 收取奏折

        Invoker invokerTwo =new Invoker(commandTwo);
        invokerTwo.action();
        // result：颁布圣旨
    }

}
