package com.ESSBG.app.Model;

import java.util.concurrent.locks.*;

public class ConcurrentCircularList<T> extends CircularList<T> {
    Lock lock;

    protected ConcurrentCircularList(Lock lock) {
        this.lock = lock;
    }

    @Override
    public T getNext(T t) {
        lock.lock();
        T e = super.getNext(t);
        lock.unlock();
        return e;
    }

    @Override
    public T getPrevious(T t) {
        lock.lock();
        T e = super.getPrevious(t);
        lock.unlock();
        return e;
    }

    @Override
    public boolean add(T t) {
        lock.lock();
        super.add(t);
        lock.unlock();
        return true;
    }

    @Override
    public boolean remove(Object t) {
        lock.lock();
        super.remove(t);
        lock.unlock();
        return true;
    }

}
