/**
 * @ClassName ArrayQueue
 * @Description 用数组实现的队列 叫 顺序队列
 * @Author Chaiphat
 * @Date 2020/8/31 20:58
 * @Version 1.0
 **/
public class ArrayQueue {

    private String[] items;

    // 数组的大小
    private int n;

    // 表示队头下标
    private int head = 0;

    // 表示队尾下标
    private int tail = 0;

    public ArrayQueue(int capacity) {
        this.items = new String[capacity];
        this.n = capacity;
    }


    // 入队操作，将item放入队尾
    public boolean enqueue(String item) {
        // tail == n表示队列末尾没有空间了
        if (tail == n) {
            // tail ==n && head==0，表示整个队列都占满了
            if (head == 0) {return false;}
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                items[i-head] = items[i];
            }
            // 搬移完之后重新更新head和tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;
    }

    public String dequeue() {
        // 表示队列为空
        if(head == tail) {
            return null;
        }

        String ret = items[head];
        ++head;
        return ret;
    }


}
