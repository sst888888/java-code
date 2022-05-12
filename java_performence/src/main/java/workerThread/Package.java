package workerThread;

/**
 * @ClassName Package
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 10:14
 * @Version 1.0
 **/
public class Package {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void execute() {
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }
}
