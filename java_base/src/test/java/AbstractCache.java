import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AbstractCache
 * @Date 2021/5/24 19:22
 * @Version 1.0
 **/
public abstract class AbstractCache<K,V> {

    protected AbstractCache() {

    }

    //  重载的方法签名不一样
    public void test(String param) {

    }

    public void test(Integer param) {

    }


    public static void main(String[] args) {
        List<? extends Number> list = new ArrayList<>();
        // list.add(new Integer(1)); 编译错误
        List<? extends Number> list2 = new ArrayList<Integer>();
    }



}
