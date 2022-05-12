package workerThread;

/**
 * @ClassName Test
 * @Description
 * @Author Chaiphat
 * @Date 2020/8/3 10:31
 * @Version 1.0
 **/
public class Test {

    public static void main(String[] args) {
        PackageChannel channel = new PackageChannel(8);
        channel.startWorker();
        // 为流水线添加包裹
        for(int i = 0; i < 100; i++) {
            Package entity = new Package();
            entity.setAddress("test");
            entity.setName("test");
            channel.put(entity);
        }
    }

}
