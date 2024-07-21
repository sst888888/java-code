package com.example.command;

/**
 * 具体命令类two，颁布圣旨
 */
public class ConcreteCommandTwo implements Command{

    // 接受者，这里可以理解为公公
    private Receiver receiver;

    public ConcreteCommandTwo(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.issue();
    }
}
