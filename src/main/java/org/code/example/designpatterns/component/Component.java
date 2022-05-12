package org.code.example.designpatterns.component;

public abstract class Component {

    private String name;
    // 设备或部门名称


    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void add(Component component);
    public abstract void remove(Component component);
    public abstract void display(int depth); // 查询该节点下的所有"设备"和"部门"

}
