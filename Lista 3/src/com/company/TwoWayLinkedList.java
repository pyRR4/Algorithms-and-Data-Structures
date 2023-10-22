package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> {
    private class Element{
        private T value;
        private Element next = null;
        private Element prev = null;
        Element(T data){
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
        public void insertAfter(Element e){
            e.setNext(this.getNext());
            e.setPrev(this);
            this.getNext().setPrev(e);
            this.setNext(e);
        }
        public void insertBefore(Element e){
            e.setNext(this);
            e.setPrev(this.getPrev());
            this.getPrev().setNext(e);
            this.setPrev(e);
        }
        public void remove(){
            if(this == head) {
                if (head.getNext() != head) {
                    head = head.getNext();
                } else
                    clear();
            }
            this.getPrev().setNext(this.getNext());
            this.getNext().setPrev(this.getPrev());
        }
    }
    private Element head = null;
    private Element getElement(int index){
        if(index < 0 || head == null)
            throw new NoSuchElementException();
        if(index == 0)
            return head;
        Element e = head.getNext();
        int counter = 1;
        while(e != head && counter < index){
            counter++;
            e = e.getNext();
        }
        if(e == head)
            throw new NoSuchElementException();
        return e;
    }
    public void add(T value) {
        Element newElem = new Element(value);
        if(head == null) {
            head = newElem;
            head.setPrev(head);
            head.setNext(head);
        }
        else {
            newElem.setNext(head);
            newElem.setPrev(head.getPrev());
            head.getPrev().setNext(newElem);
            head.setPrev(newElem);
        }
    }

    public void addAt(int index, T value) throws NoSuchElementException {
        Element newElem = new Element(value);
        if(index == 0){
            newElem.setNext(head);
            newElem.setPrev(head.getPrev());
            head.getPrev().setNext(newElem);
            head.setPrev(newElem);
            head = head.getPrev();
        }
        else {
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
        if(head.getValue().equals(value))
            return 0;
        Element e = head.getNext();
        int counter = 1;
        while(e != head && !e.getValue().equals(value)) {
            counter++;
            e = e.getNext();
        }
        if(e == head)
            return -1;
        return counter;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T removeAt(int index) throws NoSuchElementException {
        if(index < 0)
            throw new NoSuchElementException();
        Element e = getElement(index);
        T retValue = e.getValue();
        e.remove();
        return retValue;
    }

    public boolean remove(T value) {
        if(head.getValue().equals(value)){
            head.remove();
            return true;
        }
        Element e = head.getNext();
        while(e != head && !e.getValue().equals(value)){
            e = e.getNext();
        }
        if(e == head)
            return false;
        e.remove();
        return true;
    }

    public int size() {
        if(head == null)
            return 0;
        Element e = head.getNext();
        int counter = 1;
        while(e != head) {
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
        boolean wasHead = false;
        @Override
        public boolean hasNext() {
            return head != null && (current != head || !wasHead);
        }

        @Override
        public T next() {
            if(hasNext()) {
                wasHead = true;
                T retValue = current.getValue();
                current = current.getNext();
                return retValue;
            } else
                throw new NoSuchElementException();
        }
    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            int beforeIndex) throws NoSuchElementException {
//        addList(anotherList, getElement(beforeIndex));
    }

    public void insert(
            TwoWayLinkedList<T> anotherList,
            T beforeElement) throws NoSuchElementException {
//        Element e = head;
//        while(e != null && !e.getValue().equals(beforeElement)){
//            e = e.getNext();
//        }
//        if(e == null)
//            throw new NoSuchElementException();
//        addList(anotherList, e);
    }
    private void addList(TwoWayLinkedList<T> anotherList, Element e){
//        int anotherSize = anotherList.size() - 1;
//        if(e.getPrev() != null) {
//            e.getPrev().setNext(anotherList.getElement(0));
//            anotherList.getElement(0).setPrev(e.getPrev());
//        } else {
//            head = anotherList.getElement(0);
//        }
//        e.setPrev(anotherList.getElement(anotherSize));
//        anotherList.getElement(anotherSize).setNext(e);
    }
}
