package com.david.leetcode;

/**
 * 精选题解
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/hua-jie-suan-fa-24-liang-liang-jiao-huan-lian-biao/ 递归解法
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/shou-hua-tu-jie-24-liang-liang-jiao-huan-lian-biao/ 循环解法
 * <p>
 * 思路
 * 交换顺序可以用栈
 */
public class SwapPairs_24 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode node = new Solution().swapPairs(head);
        ListNode node = new Solution1().swapPairs(head);
        while (node != null) {
            System.out.printf("%s\t", node.val);
            node = node.next;
        }
    }

    /**
     * 递归解法
     * 返回值：交换完成的子链表
     * 调用单元：设需要交换的两个点为 head 和 next，head 连接后面交换完成的子链表，next 连接 head，完成交换
     * 终止条件：head 为空指针或者 next 为空指针，也就是当前无节点或者只有一个节点，无法进行交换
     */
    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;//终止条件:遍历到剩1个或者没有元素
            //重复单元:下面三行交换逻辑 当成head和next交换的
            ListNode next = head.next;//获取head.next(要交换的第二个元素)
            head.next = swapPairs(next.next);//head连接后面的交换好的链表
            next.next = head;//交换head next
            return next;//返回交换完成的子链
        }
    }

    /**
     * 循环解法
     */
    static class Solution1 {
        public ListNode swapPairs(ListNode head) {
            ListNode dummy = new ListNode(-1);//前驱结点
            dummy.next = head;
            ListNode prev = dummy;
            while (head != null && head.next != null) {
                //三步交换head和next
                ListNode next = head.next;
                head.next = next.next;
                next.next = head;
                prev.next = next;
                //两步迭代
                prev = head;
                head = head.next;
            }

            return dummy.next;
        }
    }
}
