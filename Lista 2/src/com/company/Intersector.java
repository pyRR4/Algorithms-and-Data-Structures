package com.company;

public class Intersector {
    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> list = new OneWayLinkedList<>();
        int list1It = 0;
        int list2It = 0;
        int size1 = list1.size();
        int size2 = list2.size();
        while(list1It != size1 && list2It != size2){
            if(list1.get(list1It) == list2.get(list2It)){
                list.add(list1.get(list1It));
                list1It++;
                list2It++;
            } else if (list1.get(list1It) > list2.get(list2It)) {
                list2It++;
            } else if (list2.get(list2It) > list1.get(list1It)) {
                list1It++;
            }
        }
        return list;
    }
}
class Main{
    public static void main(String[] args){

    }
}
