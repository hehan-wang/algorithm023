package com.david.leetcode;

/**
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList_206 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode newHead = new Solution().reverseList(head);
        System.out.println(newHead);
    }

    /**
     *
     */
    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;//前一个节点
            ListNode curr = head;//当前节点
            while (curr != null) {//迭代prev curr都要向后移动一位
                ListNode tmp = curr.next;//迭代
                curr.next = prev;//翻转
                prev = curr;//迭代
                curr = tmp;//迭代
            }

            return prev;
        }
    }

    /**
     * 思路：head和后面子链反转 上来直接递归到最后一个元素
     */
    static class Solution1 {
        public ListNode ReverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode newHead = ReverseList(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
}
