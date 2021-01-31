看题解说双指针。。就这？不就是先遍历一遍记个数吗？。。。然后再遍历一遍。。
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        int num=0;
        ListNode headBak=head;
        while(head!=null){
            head=head.next;
            num++;
        }
        int wher=num-k+1;
        num=1;
        while(num!=wher){
            headBak=headBak.next;
            num++;
        }
        return headBak;
    }
}
