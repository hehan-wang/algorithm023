package com.david.leetcode;

import java.util.HashSet;
import java.util.Set;

public class DetectCycle_142 {
    public static void main(String[] args) {
        ListNode n3 = new ListNode(3);
        ListNode n2 = new ListNode(2);
        ListNode n0 = new ListNode(0);
        ListNode n4 = new ListNode(4);
        n3.next = n2;
        n2.next = n0;
        n0.next = n4;
        n4.next = n2;
        ListNode head = n3;
//        ListNode node = new Solution().detectCycle(head);
        ListNode node = new Solution1().detectCycle(head);
        System.out.println(node);
    }

    /**
     * 双指针法
     * slow走一步 fast走两步
     * 设：链表长度a 环长度b 快指针走f 慢指针走s
     * 求：a的元素(a为环的头结点)
     * 1.如果快指针直接走到最后 返回null 没有环
     * 2.第一次相遇 f=s+nb f=2s 得 s=nb(慢指针走nb)
     * 3.第二次相遇 因为a+nb在入口慢指针在走a即可。把快指针调到head 相遇时即为入口
     * 总结：
     * 走a+nb步一定是在环入口
     * 第一次相遇时慢指针已经走了nb步
     */
    public static class Solution1 {
        public ListNode detectCycle(ListNode head) {
            ListNode fast = head, slow = head;
            while (true) {//第一次相遇
                if (fast == null || fast.next == null) return null;//fast走到头了 没有找到
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) break;//第一次相遇
            }
            //第二次相遇 在向前走a长度
            fast = head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return fast;
        }
    }

    //set排重法 跟141一样
    public static class Solution {
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> set = new HashSet();
            while (head != null) {
                if (set.contains(head)) return head;
                set.add(head);
                head = head.next;
            }
            return null;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ListNode{");
            sb.append("val=").append(val);
            sb.append('}');
            return sb.toString();
        }
    }
}
