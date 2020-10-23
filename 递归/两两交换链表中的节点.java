给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



示例 1：（注意以下的输入格式暂且别管，知道是一个链表即可，链表的类定义在如下代码开头处）


输入：head = [1,2,3,4]
输出：[2,1,4,3]
示例 2：

输入：head = []
输出：[]
示例 3：

输入：head = [1]
输出：[1]

提示：链表中节点的数目在范围 [0, 100] 内,所以加了一堆基本结束条件判断是不是空节点。
0 <= Node.val <= 100
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
 
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return head;
        }
        if(head.next==null){
            return head;
        }
        if(head.next.next==null){
            ListNode returnResult = head.next;
            head.next.next=head;
            head.next = null;
            return returnResult;
        }
        
        ListNode middleResult = swapPairs(head.next.next);
        head.next.next=head;
        ListNode returnResult = head.next;
        head.next= middleResult;
        return returnResult;
    }
}


