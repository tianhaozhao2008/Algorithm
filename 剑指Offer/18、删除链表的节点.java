剑指 Offer 18. 删除链表的节点 https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
返回删除后的链表的头节点。

示例 1:
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.

思路：就是直接遍历即可。对于起始节点特殊考虑。
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        if(head.val==val){
            return head.next;
        }
        ListNode x1=head;
        ListNode x2=head.next;
        while(x2.val!=val){
            x1=x2;
            x2=x2.next;
        }
        x1.next=x2.next;
        return head;
    }
}
优化方法就是设置一个“哨兵节点”，挂在起始节点前面，这样就可以当成所有的来遍历了，无需特殊考虑，代码更简洁易维护。
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode sentry=new ListNode(0); 
        sentry.next=head;
        //以上两行是建立哨兵节点
        ListNode x1=sentry;
        ListNode x2=sentry.next;
        while(x2.val!=val){
            x1=x2;
            x2=x2.next;
        }
        x1.next=x2.next;
        return sentry.next;
    }
}
