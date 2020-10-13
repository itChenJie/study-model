package com.skipList;

/**
 * @Description 跳表
 *  有序链表当中快速查询元素
 * @Author ChenWenJie
 * @Data 2020/8/26 9:36 下午
 **/
public class SkipListTest {


    public static void main(String[] args) {
        SkipList list=new SkipList();
        list.insert(50);
        list.insert(15);
        list.insert(13);
        list.insert(20);
        list.insert(100);
        list.insert(75);
        list.insert(99);
        list.insert(76);
        list.insert(83);
        list.insert(65);
        list.printList();
        list.search(50);
        list.remove(50);
        list.search(50);
    }

}
