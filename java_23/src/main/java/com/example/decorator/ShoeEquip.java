package com.example.decorator;


public class ShoeEquip implements IEquip{
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "shoe";
    }
}
