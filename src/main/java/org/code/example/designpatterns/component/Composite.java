package org.code.example.designpatterns.component;

import java.util.ArrayList;

public class Composite extends Component {

    public Composite(String name) {
        super(name);
    }

    // 构建一个容器，用来保存该节点下所有的“设备”和“组织”
    private ArrayList<Component> componentArrayList = new ArrayList<>();

    @Override
    public void add(Component component) {
        this.componentArrayList.add(component);
    }

    @Override
    public void remove(Component component) {
        this.componentArrayList.remove(component);
    }

    @Override
    public void display(int depth) {
//        System.out.println("depth:"+depth);
        // 输出树形结构
        for(int i = 0;i<depth;i++){
            System.out.println("*");
        }
        System.out.println(getName());
        // 递归显示
        componentArrayList.forEach(component -> component.display(1));
//        System.out.println("-------End-------");
    }
}
