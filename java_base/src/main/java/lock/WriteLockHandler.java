package lock;

/**
 * @author: cp
 * @date: 2024-01-09 13:11
 */
public interface WriteLockHandler<T> {

    public void handler(T t);
}
