package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> {
    private class Element {
        private T value;
        private Element next = null;
        private Element prev = null;

        Element(T data) {
            this.value = data;
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

        public Element getPrev() {
            return prev;
        }

        public void setPrev(Element prev) {
            this.prev = prev;
        }

        public void insertAfter(Element e) {
            if (this.getNext() != null)
                e.setNext(this.getNext());
            e.setPrev(this);
            if (this.getNext() != null)
                this.getNext().setPrev(e);
            this.setNext(e);
        }

        public void remove() {
            if (this == head) {
                head = head.getNext();
                if(head != null)
                    head.setPrev(null);
            } else if (this.getNext() == null) {
                this.getPrev().setNext(null);
            } else {
                this.getPrev().setNext(this.getNext());
                this.getNext().setPrev(this.getPrev());
            }
        }
    }
    private Element head = null;

    private Element getElement(int index) {
        if (index < 0 || head == null)
            throw new NoSuchElementException();
        if (index == 0)
            return head;
        Element e = head.getNext();
        int counter = 1;
        while (e != null && counter < index) {
            counter++;
            e = e.getNext();
        }
        if (e == null)
            throw new NoSuchElementException();
        return e;
    }

    public void add(T value) {
        Element newElem = new Element(value);
        if (head == null) {
            head = newElem;
        } else {
            Element e = head;
            while (e.getNext() != null)
                e = e.getNext();
            e.insertAfter(newElem);
        }
    }

    public void addAt(int index, T value) throws NoSuchElementException {
        Element newElem = new Element(value);
        if (index == 0) {
            newElem.setNext(head);
            head.setPrev(newElem);
            head = head.getPrev();
        } else {
            Element e = getElement(index - 1);
            e.insertAfter(newElem);
        }
    }

    public void clear() {
        head = null;
    }

    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    public T get(int index) throws NoSuchElementException {
        Element e = getElement(index);
        return e.getValue();
    }

    public void set(int index, T value) throws NoSuchElementException {
        Element e = getElement(index);
        e.setValue(value);
    }

    public int indexOf(T value) {
        Element e = head;
        int counter = 0;
        while (e != null && !e.getValue().equals(value)) {
            counter++;
            e = e.getNext();
        }
        if (e == null)
            return -1;
        return counter;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T removeAt(int index) throws NoSuchElementException {
        if (index < 0)
            throw new NoSuchElementException();
        Element e = getElement(index);
        T retValue = e.getValue();
        e.remove();
        return retValue;
    }

    public boolean remove(T value) {
        Element e = head;
        while (e != null && !e.getValue().equals(value)) {
            e = e.getNext();
        }
        if (e == null)
            return false;
        e.remove();
        return true;
    }

    public int size() {
        Element e = head;
        int counter = 0;
        while (e != null) {
            counter++;
            e = e.getNext();
        }
        return counter;
    }

    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        Element current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T retValue = current.getValue();
                current = current.getNext();
                return retValue;
            } else
                throw new NoSuchElementException();
        }
    }
}
