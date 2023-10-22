package com.company;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        TwoWayLinkedList<Integer> distinctList = new TwoWayLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        Integer tmp;
        Integer latestValue = null;
        while(iterator.hasNext()){
            if(!(tmp = iterator.next()).equals(latestValue)){
                distinctList.add(tmp);
                latestValue = tmp;
            }
        }
        return distinctList;
    }
}
