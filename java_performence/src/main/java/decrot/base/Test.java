package decrot.base;

/**
 * @ClassName Test
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 14:50
 * @Version 1.0
 **/
public class Test {
    public static void main(String[] args) {
        IDecorator decorator = new Decorator();
        IDecorator curtainDecorator = new CurtainDecorator(decorator);
        curtainDecorator.decorate();
    }
}
