package com.example.cd;

/**
 * @author: cp
 * @date: 2023-09-26 17:21
 */
public class ComputerOnCommand implements Command {

    private Computer computer;

    public ComputerOnCommand(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void execute() {
        computer.on();
    }

}
