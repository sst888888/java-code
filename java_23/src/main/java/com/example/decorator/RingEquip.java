package com.example.decorator;


public class RingEquip implements IEquip{
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "ring";
    }
}
