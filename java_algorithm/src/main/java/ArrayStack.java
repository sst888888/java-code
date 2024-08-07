/**
 * @ClassName ArrayStack
 * @Description 基于数组实现的顺序栈 后进先出
 **/
public class ArrayStack {

    private String[] items;
    // 栈中元素个数
    private int count;
    // 栈的大小
    private int n;

    public ArrayStack(int n) {
        this.items = new String[n];
        this.n = n;
        this.count = 0;
    }

    // 入栈操作 后进先出
    public boolean push(String item) {
        if (count == n) {
            return false;
        }
        items[count] = item;
        ++count;
        return true;
    }

    // 出栈操作 后进先出
    public String pop() {
        if (count == 0) {
            return null;
        }
        String tmp = items[count - 1];
        --count;
        return tmp;
    }

}
