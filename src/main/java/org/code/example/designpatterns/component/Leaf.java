package org.code.example.designpatterns.component;

public class Leaf extends Component {


    public Leaf(String name) {
        super(name);
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException("叶子结点（设备）不能挂载设备");
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException("叶子结点（设备）不能移除设备");
    }

    @Override
    public void display(int depth) {
        for(int i = 0;i<depth;i++){
            System.out.println("*");
        }
        System.out.println(getName());
    }
}
