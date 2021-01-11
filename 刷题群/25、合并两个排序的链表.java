其实就是归并排序后的合并操作。
1.注意对象初始化时，不要ListNode sentry=null，不然就相当于没有new对象、没有为对象开辟内存空间。那么你sentry.val=4时就会报错，因为还没有对象呢。
   关于Java的函数，它传递的是值而不是地址（同C语言），因此你如果传入引用类型，那么是可以对引用类型指向的对象做修改的，但是不能对引用类型本身的值进行修改
   （因为引用类型本质上就是指针）
2.以后一般链表的题都直接加上哨兵节点，会避免一些麻烦的边界问题
3.这题一开始while的条件那块弄错了导致bug。

下面这个还可以改进一下就是当一个链表已经空了，就直接把另一个拼过来，就不用继续遍历了。
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry=new ListNode(0); //哨兵节点
        ListNode head=sentry;
        while(!(l1==null &&l2==null)){
            if(l1==null){
                sentry.next=new ListNode(l2.val);
                l2=l2.next;
                sentry=sentry.next;
            }
            else if(l2==null){
                sentry.next=new ListNode(l1.val);
                l1=l1.next;
                sentry=sentry.next;
            }
            else{
                if(l1.val>l2.val){
                    sentry.next=new ListNode(l2.val);
                    l2=l2.next;
                    sentry=sentry.next;
                }
                else{
                    sentry.next=new ListNode(l1.val);
                    l1=l1.next;
                    sentry=sentry.next;
                }
            }
        }
        return head.next;
    }
}
