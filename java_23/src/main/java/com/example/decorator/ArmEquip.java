package com.example.decorator;


public class ArmEquip implements IEquip{
    @Override
    public int calculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "🔪";
    }
}
