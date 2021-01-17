package com.david.leetcode;

public class ReverseKGroup_25 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode newHead = new Solution().reverseKGroup(head, 3);
        //遍历
        while (newHead != null) {
            System.out.printf("%s \t", newHead.val);
            newHead = newHead.next;
        }
    }

    /**
     * 使用递归
     */
    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || head.next == null) return head; //终止条件
            ListNode tail = head;
            for (int i = 0; i < k; i++) {
                if (tail == null) return head;//如果链表长度小于k 不翻转
                tail = tail.next;//迭代找出最后一个节点
            }
            ListNode newHead = reverse(head, tail);//翻转head-tail
            head.next = reverseKGroup(tail, k);//把切断的子链翻转后接到head后面(现在head为最后一个节点)
            return newHead;
        }

        //翻转链表
        public ListNode reverse(ListNode head, ListNode tail) {
            ListNode pre = null;
            ListNode curr = head;
            while (curr != tail) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }
            return pre;
        }
    }

}
