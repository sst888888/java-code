package decrot.base;

public abstract class BaseDecorator implements IDecorator{

    private final IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    @Override
    public void decorate() {
        if(decorator != null) {
            decorator.decorate();
        }
    }
}