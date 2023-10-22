package com.company;

import java.util.Iterator;

public class Merger {
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        int list1It = 0;
        int list2It = 0;
        int size1 = list1.size();
        int size2 = list2.size();
        boolean finished = false;
        while(!finished) {
            if (list1.get(list1It) <= list2.get(list2It)) {
                list.add(list1.get(list1It));
                list1It++;
                if(list1It == size1) {
                    for(int i = list2It; i < size2; i++)
                        list.add(list2.get(i));
                    finished = true;
                }
            } else {
                list.add(list2.get(list2It));
                list2It++;
                if(list2It == size2) {
                    list2.clear();
                    for(int i = list1It; i < size1; i++)
                        list.add(list1.get(i));
                    finished = true;
                }
            }
        }
        return list;
    }
}
