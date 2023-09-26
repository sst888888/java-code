package com.example.decorator.impl;


import com.example.decorator.IEquip;
import com.example.decorator.IEquipDecorator;

public class RedGemDecorator implements IEquipDecorator {

    // 维护一个装备
    private IEquip equip;

    public RedGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 15 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + " red gem;";
    }
}
