package com.ESSBG.app.Model;

import java.util.concurrent.locks.*;

/**
 * Author: Björn Rosengren
 *
 * Wrapper class to make the circular list free from race conditions.
 */
public class ConcurrentCircularList<T> extends CircularList<T> {
    Lock lock;

    protected ConcurrentCircularList() {
        this.lock = new ReentrantLock(true);
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

    @Override
    public T get(int i) {
        lock.lock();
        T e = super.get(i);
        lock.unlock();
        return e;
    }
}
