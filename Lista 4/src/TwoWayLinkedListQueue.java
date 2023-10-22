package com.company;

import com.company.exceptions.*;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {
    private TwoWayLinkedList<T> list;
    private int capacity;
    private int size;

    public TwoWayLinkedListQueue(int capacity) {
        list = new TwoWayLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return capacity == size;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if(isFull())
            throw new FullQueueException();
        list.add(value);
        size++;
    }

    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        return list.get(0);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        size--;
        return list.removeAt(0);
    }

    @Override
    public int size() {
        return size;
    }
}