剑指 Offer 06. 从尾到头打印链表 https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

解法很简单，弄个栈，先遍历链表依次压栈，然后生成一个栈的size的数组，然后依次弹栈填写数组即可。
（注意一下面试可能会让你自己写测试用例，测试用例考察的是比如边缘值的选择等。
因此写测试用例要自己写一个链表，应该就是弄个节点类即可）
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        while(head!=null){
            stack.push(head.val);
            head=head.next;
        }
        int size=stack.size();
        int[]list = new int[size];
        for(int i=0;i<size;i++){ //注意这里循环的size不能直接用stack.size()，因为它的值会随着pop不断变化，我擦debug了十几分钟
            list[i]=stack.pop();
        }
        return list;
    }
}
