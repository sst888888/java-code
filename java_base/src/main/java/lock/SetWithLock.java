package lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: cp
 * @date: 2024-01-09 13:27
 */
public class SetWithLock<T> extends ObjWithLock<Set<T>>{
    private static final long serialVersionUID = -3332399499966988865L;
    private static Logger log = LoggerFactory.getLogger(SetWithLock.class);

    public SetWithLock(Set<T> set) {
        super(set);
    }

    public SetWithLock(Set<T> set, ReentrantReadWriteLock lock) {
        super(set, lock);
    }

    public int size() {
        ReentrantReadWriteLock.ReadLock readLock = this.readLock();
        readLock.lock();
        try {
            Set<T> set = this.getObj();
            return set.size();
        } finally {
            readLock.unlock();
        }
    }
    public boolean remove(T t){
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            return set.remove(t);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return false;
    }

    public void clear() {
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            set.clear();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean add(T t){
        ReentrantReadWriteLock.WriteLock writeLock = this.writeLock();
        writeLock.lock();
        try {
            Set<T> set = this.getObj();
            return set.add(t);
        } catch (Throwable e) {
           log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
        return false;
    }
}
