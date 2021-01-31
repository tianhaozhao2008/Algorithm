这种要在纸上画图去看：就是设置三个指针，分别为left、mid、和right三个节点，遍历——依次把指针反转过来即可。（也有用递归做的）
（关于引用和对象的关系要搞清楚，引用.value或引用.next就是对象本尊了，而引用本身只是绳子，因此再切换绳子和对象的时候要搞清楚）

注意边界条件：第一个节点特殊处理因为要指向NULL，最后一个节点也特殊处理因为没有next节点了
注意特殊情况：只有一个节点或者输入的是null 空节点。

class Solution {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next==null)return head;
        ListNode mid = head.next;
        head.next=null;
        ListNode left = head;
        while(mid.next!=null){
            ListNode right = mid.next;
            mid.next=left;
            left=mid;
            mid=right;
        }
        mid.next=left;
        return mid;
    }
}
