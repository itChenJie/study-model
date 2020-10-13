package com.skipList;

import java.util.Random;

/**
 * @Description 跳表
 * @Author ChenWenJie
 * @Data 2020/8/26 9:38 下午
 **/
public class SkipList {
    //结点“晋升”的概率
    private static final double PROMOTE_RATE = 0.5;
    private Node head,tail;
    private int maxLevel;

    public SkipList() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    /**
     * 查询节点
     * @param data
     * @return
     */
    public Node search(int data){
        Node node= findNode(data);
        if (node.data == data){
            System.out.println("找到结点：" + data);
            return node;
        }else {
            System.out.println("未找到结点：" + data);
        }
        return null;
    }

    /**
     * 插入结点
     * @param data
     */
    public void insert(int data){
        Node preNode= findNode(data);
        if (preNode.data == data){
            return;
        }
        Node newNode = new Node(data);
        appendNode(preNode, newNode);
        int currentLevel=0;
        //随机决定结点是否“晋升”
        Random random = new Random();
        while (random.nextDouble() < PROMOTE_RATE) {
            if (currentLevel == maxLevel){
                addLevel();
            }

            //找到上一层的前置节点
            while (preNode.up==null) {
                preNode=preNode.left;
            }
            preNode=preNode.up;
            //把“晋升”的新结点插入到上一层
            Node upperNode = new Node(data);
            appendNode(preNode, upperNode);
            upperNode.down = newNode;
            newNode.up = upperNode;
            newNode = upperNode;
            currentLevel++;
        }
    }

    /**
     * 增加一层
     */
    private void addLevel() {
        maxLevel++;
        Node minNode = new Node(Integer.MIN_VALUE);
        Node maxNode = new Node(Integer.MAX_VALUE);
        minNode.right = maxNode;
        maxNode.left = minNode;
        minNode.down = head;
        head.up = minNode;
        maxNode.down = tail;
        tail.up = maxNode;
        head = minNode;
        tail = maxNode;
    }

    /**
     * 在前置结点后面添加新结点
     * @param parentNode
     * @param newNode
     */
    private void appendNode(Node parentNode, Node newNode) {
        newNode.left = parentNode;
        newNode.right = parentNode.right;
        parentNode.right.left = newNode;
        parentNode.right = newNode;
    }

    /**
     * 找到值对应的前置结点
     * @param data
     * @return
     */
    private Node findNode(int data) {
        Node node = head;
        while (true){
            // 1：判断当前层链表的右则是否不等于Integer的最大值
            // 2：当前层的右则节点对应的值是否小于对于当前查找的数
            if (node.right.data != Integer.MAX_VALUE && node.right.data <= data){
                node = node.right;
            }
            if (node.down == null){
                break;
            }
            // 处理下一层
            node = node.down;
        }
        return node;
    }

    /**
     * 删除结点
     * @param data
     * @return
     */
    public boolean remove(int data) {
        Node removedNode = search(data);
        if (removedNode == null) {
            return false;
        }
        int currentLevel = 0;
        while (removedNode != null){
            removedNode.right.left = removedNode.left;
            removedNode.left.right = removedNode.right;
            //如果不是最底层，且只有无穷小和无穷大结点，删除该层
            if (currentLevel != 0 && removedNode.left.data == Integer.MIN_VALUE && removedNode.right.data == Integer.MAX_VALUE){
                removeLevel(removedNode.left);
            }else {
                currentLevel++;
            }
            removedNode = removedNode.up;
        }
        return true;
    }
    /**
     * 删除一层
     * @param leftNode
     */
    private void removeLevel(Node leftNode){
        Node rightNode = leftNode.right;
        // 如果删除层是最高层
        if (rightNode.up == null){
            leftNode.down.up = null;
            rightNode.down.up = null;
        }else {
            leftNode.up.down = leftNode.down;
            leftNode.down.up = leftNode.up;
            rightNode.up.down = rightNode.down;
            rightNode.down.up = rightNode.up;
        }
        maxLevel--;
    }

    /**
     * 输出底层链表
     */
    public void printList() {
        Node node = this.head;
        while (node.down != null){
            node = node.down;
        }
        while (node.right.data != Integer.MAX_VALUE){
            System.out.print(node.right.data + " ");
            node = node.right;
        }
        System.out.println();


    }
}
