package lock;

import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;


/**
 * @author: cp
 * @date: 2024-01-08 22:46
 * 自带读写锁的对象
 */
public class ObjWithLock<T> implements Serializable {
    private static final long serialVersionUID = -333239949999988865L;
    private static Logger log = LoggerFactory.getLogger(ObjWithLock.class);

    private T obj = null;
    private ReentrantReadWriteLock lock = null;

    public ObjWithLock(T obj) {
        this(obj, new ReentrantReadWriteLock());
    }

    public ObjWithLock(T obj, ReentrantReadWriteLock lock){
        super();
        this.obj = obj;
        this.lock = lock;
    }


    public ReentrantReadWriteLock getLock(){return lock;}

    public ReentrantReadWriteLock.WriteLock writeLock() {return lock.writeLock();}

    public ReentrantReadWriteLock.ReadLock readLock(){return lock.readLock();}


    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    /**
     * 操作obj时 带上读锁
     * @param readLockHandler
     */
    public void handle(ReadLockHandler<T> readLockHandler){
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            readLockHandler.handler(obj);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 操作obj时 带上写锁
     * @param writeLockHandler
     */
    public void handle(WriteLockHandler<T> writeLockHandler){
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            writeLockHandler.handler(obj);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        } finally {
            writeLock.unlock();
        }
    }
}
