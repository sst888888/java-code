package com.example.decorator;

import com.example.decorator.impl.BlueGemDecorator;
import com.example.decorator.impl.RedGemDecorator;
import com.example.decorator.impl.YellowGemDecorator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) {
        log.info("two red, one blue, shoe");
        IEquip equip = new RedGemDecorator(new RedGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        log.info("attack is {}, description is {}", equip.calculateAttack(), equip.description());

        log.info("one red, one blue, one yellow, arm");
        equip = new RedGemDecorator(new YellowGemDecorator(new BlueGemDecorator(new ArmEquip())));
        log.info("attack is {}, description is {}", equip.calculateAttack(), equip.description());

    }

}
