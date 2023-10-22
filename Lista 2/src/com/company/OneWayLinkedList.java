package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {
    private class Element{
        private T value;
        private Element next;
        Element(T data){
            value = data;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }
    private Element head = null;
    private Element getElement(int index){
        Element actElem = head;
        while(index > 0 && actElem != null){
            index--;
            actElem = actElem.getNext();
        }
        if(actElem == null)
            throw new NoSuchElementException();
        return actElem;
    }
    @Override
    public void add(T value) {
        Element e = new Element(value);
        if(head == null)
            head = e;
        else {
            Element tail = head;
            while (tail.getNext() != null){
                tail = tail.getNext();
            }
            tail.setNext(e);
        }
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if(index < 0)
            throw new NoSuchElementException();
        Element e = new Element(value);
        if(index == 0) {
            e.setNext(head);
            head = e;
        }
        else {
            Element elBeforeIndex = getElement(index - 1);
            e.setNext(elBeforeIndex.getNext());
            elBeforeIndex.setNext(e);
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        Element e = getElement(index);
        return e.getValue();
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        Element actElem = getElement(index);
        actElem.setValue(value);
    }

    @Override
    public int indexOf(T value) {
        int pos = 0;
        Element actElem = head;
        while(actElem != null)
        {
            if(actElem.getValue() == value)
                return pos;
            pos++;
            actElem = actElem.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        if(index < 0)
            throw new NoSuchElementException();
        if(index == 0){
            T retValue = head.getValue();
            head = head.getNext();
            return retValue;
        }
        Element e = getElement(index-1);
        if(e.getNext() == null)
            throw new NoSuchElementException();
        T retValue = e.getNext().getValue();
        e.setNext(e.getNext().getNext());
        return retValue;
    }

    @Override
    public boolean remove(T value) {
        if(isEmpty())
            return false;
        if(head.getValue().equals(value)){
            head = head.getNext();
            return true;
        }
        Element actElem = head;
        while(actElem.getNext() != null && !actElem.getNext().getValue().equals(value))
            actElem = actElem.getNext();
        if(actElem.getNext() == null)
            return false;
        actElem.setNext(actElem.getNext().getNext());
        return true;
    }

    @Override
    public int size() {
        int pos=0;
        Element actElem = head;
        while(actElem != null) {
            pos++;
            actElem=actElem.getNext();
        }
        return pos;
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        Element e;
        public OneWayLinkedListIterator(){
            this.e = head;
        }
        @Override
        public boolean hasNext() {
            return e != null;
        }

        @Override
        public T next() {
            if(hasNext()) {
                T value = e.getValue();
                e = e.getNext();
                return value;
            }
            else
                throw new NoSuchElementException();
        }
    }
}

