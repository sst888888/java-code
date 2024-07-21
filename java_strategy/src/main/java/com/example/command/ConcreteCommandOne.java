package com.example.command;

/**
 * 具体命令类one，收取奏折命令
 */
public class ConcreteCommandOne implements Command{

    // 接受者，这里可以理解为公公
    private Receiver receiver;

    public ConcreteCommandOne(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.charge();
    }
}
