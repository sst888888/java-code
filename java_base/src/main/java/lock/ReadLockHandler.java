package lock;

/**
 * @author: cp
 * @date: 2024-01-09 13:11
 */
public interface ReadLockHandler<T> {

    public void handler(T t);
}
