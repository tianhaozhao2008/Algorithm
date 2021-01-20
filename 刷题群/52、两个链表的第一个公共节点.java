首先想到的就是两条链表同时遍历，遍历的同时把节点放入set，放入时判断如果set里已经有了就说明这个是公共节点（因为链表中没用环），
时间和空间都是O（n）的。

时间复杂度肯定要遍历一遍、是优化不了的。空间复杂度如何变成O（1）即不用set记录呢？想了几分钟发现可以先统计一下两条链表的长度，
然后让长的链表先多走几步，当两个指针指向的链表一样长时，就让两个指针每次前移动1格，判断节点是否相同（不能为null，null因为也算相同），
这样空间复杂度就是O（1）了。
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null || headB==null)return null;
        int lengthA=0;
        int lengthB=0;
        ListNode A=headA;
        ListNode B=headB;
        while(A!=null){
            lengthA++;
            A=A.next;
        }
        while(B!=null){
            lengthB++;
            B=B.next;
        }
        A=headA;
        B=headB;
        if(lengthA>lengthB){
            int compare=lengthA-lengthB;
            while(compare!=0){
                A=A.next;
                compare--;
            }
        }
        else{
            int compare=lengthB-lengthA;
            while(compare!=0){
                B=B.next;
                compare--;
            }
        }
        while(A!=null){
            if(A==B)return A;
            A=A.next;
            B=B.next;
        }
        return null;
    }
}

题解中还有方法是两个指针，A链表的指针遍历完后就指向B链表继续遍历；B链表的指针遍历完后就指向A链表继续遍历。
这样如果它们有相交的话，就会出现两个指针指向的对象是同一个对象。 时间空间复杂度跟我的一样，本质上就一样。这里不写了。
