package com.example.cd;


public class Test {

    public static void main(String[] args) {
        Light light = new Light();
        Computer computer = new Computer();
        Door door = new Door();

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setCommand(0, new LightOnCommand(light));
        controlPanel.setCommand(1, new LightOffCommand(light));
        controlPanel.setCommand(2, new ComputerOnCommand(computer));
        controlPanel.setCommand(3, new ComputerOffCommand(computer));
        controlPanel.setCommand(4, new DoorOpenCommand(door));
        controlPanel.setCommand(5, new DoorCloseCommand(door));

        controlPanel.keyPressed(0);
        controlPanel.keyPressed(1);
        controlPanel.keyPressed(2);
        controlPanel.keyPressed(3);
        controlPanel.keyPressed(4);
        controlPanel.keyPressed(5);
        controlPanel.keyPressed(8);


        QuickCommand quickCommand = new QuickCommand(new Command[]{new DoorCloseCommand(door), new LightOffCommand(light),
        new ComputerOnCommand(computer)});

        controlPanel.setCommand(8, quickCommand);
        controlPanel.keyPressed(8);
    }
}
