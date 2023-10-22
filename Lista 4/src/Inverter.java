package com.company;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;

public class Inverter {

    // Odwróć kolejność elementów w kolejce wykorzystując do tego stos zaimplementowany w klasie ArrayStack
    public static <T> void invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException, FullStackException {
        ArrayStack<T> inverter = new ArrayStack<>(queue.size());
        while(!queue.isEmpty()){
            inverter.push(queue.dequeue());
        }
        while(!inverter.isEmpty()){
            queue.enqueue(inverter.pop());
        }
    }
}
