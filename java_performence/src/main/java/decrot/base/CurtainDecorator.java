package decrot.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName CurtainDecorator
 * @Description 窗帘装饰类
 * @Author Chaiphat
 * @Date 2020/8/3 14:47
 * @Version 1.0
 **/
@Slf4j
public class CurtainDecorator extends BaseDecorator {
    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    @Override
    public void decorate() {
        log.info("窗帘装饰。。。");
        super.decorate();
    }
}
