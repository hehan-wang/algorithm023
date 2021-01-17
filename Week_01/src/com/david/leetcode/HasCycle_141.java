package com.david.leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题解9种：https://leetcode-cn.com/problems/linked-list-cycle/solution/3chong-jie-jue-fang-shi-liang-chong-ji-bai-liao-10/
 * 1.快慢指针 快指针肯定套圈慢指针
 * 2.使用hash表存储走过节点 遇到相同节点则有环
 */
public class HasCycle_141 {
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
//        boolean hasCycle = new Solution().hasCycle(head);
        boolean hasCycle = new Solution1().hasCycle(head);
        System.out.println(hasCycle);
    }

    /**
     * 快慢指针法
     * time:O(n) 确切的说是 O(n/2) 因为快指针每次走两步
     */
    public static class Solution1 {
        public boolean hasCycle(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null) {//快指针走到头了
                fast = fast.next.next;//快指针每次走两步
                slow = slow.next;//慢指针每次走一步
                if (fast == slow) return true;//存在快慢指针相遇则有环
            }
            return false;//遍历到链表末尾还没找到返回false
        }
    }

    /**
     * 暴力法
     * time:O(n)
     */
    public static class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            while (head != null) {
                if (set.contains(head)) return true;
                set.add(head);
                head = head.next;
            }
            return false;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
