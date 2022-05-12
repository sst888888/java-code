package org.code.example.sort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ClassName MergeKSortedList23
 * @Description 合并 K 个排序链表
 * @Author Chaiphat
 * @Date 2020/3/12 15:42
 * @Version 1.0
 **/
public class MergeKSortedList23 {

    static class ListNode{
        int val;
        ListNode next;
        ListNode(int x){
            val = x;
            next = null;
        }
    }

    /**
     * 用堆排序每个队列的头节点
     * 时间复杂度O(Nlongk)
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists){
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        PriorityQueue<ListNode> pqueue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for(ListNode list : lists){
            if(list != null){
                pqueue.offer(list);
            }
        }

        while(!pqueue.isEmpty()){
            ListNode node = pqueue.poll();
            current.next = node;
            current = current.next;
            if(node.next != null){
                pqueue.add(node.next);
            }
        }

        return dummy.next;
    }

    /**
     * 两个list合并，直到合并完成
     * 时间复杂度O(kN)
     * @param lists
     * @return
     */
    public static ListNode mergeKListsV1(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }
        ListNode list1 = lists[0];
        for(int i = 1; i < lists.length; i++){
            list1 = merget2List(list1,lists[i]);
        }

        return list1;
    }

    private static ListNode merget2List(ListNode node1, ListNode node2){
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(node1 != null && node2 != null){
            if(node1.val < node2.val){
                current.next = node1;
                node1 = node1.next;
            }else {
                current.next = node2;
                node2 = node2.next;
            }
            current = current.next;
        }

        while(node1 != null){
            current.next = node1;
            node1 = node1.next;
            current = current.next;
        }

        while(node2 != null){
            current.next = node2;
            node2 = node2.next;
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * 分治法，从代码来看和两两合并很类似，但是比较次数少了很多。
     * 时间复杂度O(Nlogk)
     * @param lists
     * @return
     */
    public static ListNode mergeKListsV3(ListNode[] lists){
        if(lists == null || lists.length == 0){
            return null;
        }

        int n = lists.length;
        int interval = 1;
        while(interval < n){
            for(int i = 0; i < n - interval; i+=2*interval){
                lists[i] = merget2List(lists[i],lists[i+interval]);
            }
            interval = interval*2;
        }

        return lists[0];
    }


}
