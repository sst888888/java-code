package com.example.decorator.impl;


import com.example.decorator.IEquip;
import com.example.decorator.IEquipDecorator;

public class YellowGemDecorator implements IEquipDecorator {

    // 维护一个装备
    private IEquip equip;

    public YellowGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 10 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + " yellow gem; ";
    }
}
