package decrot.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Decorator
 * @Description 装修基本类
 * @Author Chaiphat
 * @Date 2020/8/3 14:42
 * @Version 1.0
 **/
@Slf4j
public class Decorator implements IDecorator {
    @Override
    public void decorate() {
        log.info("水电装修、天花板装修及粉刷墙。。。");
    }
}
