package com.skipList;

/**
 * @Description 链表结点类
 * @Author ChenWenJie
 * @Data 2020/8/26 9:38 下午
 **/
public class Node {
    public int data;
    /**
     * 跳表结点的前后和上下都有指针
     */
    public Node up, down, left, right;

    public Node(int data) {
        this.data = data;
    }
}