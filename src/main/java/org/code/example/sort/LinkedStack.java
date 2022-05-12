package org.code.example.sort;


/**
 * @ClassName LinkedStack
 * @Description 链表实现一个链式栈
 * @Author Chaiphat
 * @Date 2020/3/19 21:41
 * @Version 1.0
 **/
public class LinkedStack {

    private Node top = null;

    public static class Node{
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData(){
            return data;
        }
    }

    public void push(String item){
        Node newNode = new Node(item,null);
        if(top == null){
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public String pop(){
        if(top == null){
            return null;
        }
        String value = top.data;
        top = top.next;
        return value;
    }

    public void printAll(){
        Node pNode = top;
        while (pNode != null){
            System.out.println(pNode.data + " ");
            pNode = pNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push("haha");
        linkedStack.push("nihao");
        linkedStack.push("hello");
        linkedStack.printAll();
        linkedStack.pop();
        System.out.println("----------");
        linkedStack.printAll();
    }

}
