这题就是深拷贝链表。

方法1：我的方法
一开始我直接当成二叉树做了，就是递归，递归的参数是新建的new节点对象和要拷贝的节点，然后对左子节点和右子节点做递归。
结果这题的ramdom指针的路子相当野，还可以往回指向从而形成无限循环，因此我第一次的代码超时了。

于是我在递归函数开始的时候把递归的两个参数加入HashMap，代表这个节点已经创建过，然后下次递归调用自身的时候，
先看有没有拷贝过，如果拷贝过则直接把当前节点的指针指过去，就不递归调用自身了。
class Solution {
   Map<Node,Node>map=new HashMap<>();
   
   public Node copyRandomList(Node head) {
        if(head==null)return null;
        Node res=new Node(0);
        recur(res,head);
        return  res;
    }
    void recur(Node res,Node head){
        map.put(head,res);
        res.val=head.val;

        if(head.next!=null){
            if(map.containsKey(head.next))res.next=map.get(head.next);
            else{
                res.next=new Node(0);
                recur(res.next,head.next);
            }
        }
        if(head.random!=null){
            if(map.containsKey(head.random))res.random=map.get(head.random);
            else{
                res.random=new Node(0);
                recur(res.random,head.random);
            }
        }
    }
}

方法2：题解的第一种方法，此时问题已经简单化了，我刚才没审清题，把问题复杂化了。题目要求是一个普通链表，然后加上random指针，random指针指向的只能是链表中的节点，
而我把它当成也可以指向链表外的节点了。
所以我刚才很疑惑dfs递归做的题为何也能用迭代做。。
思路很简单：先遍历一遍，把原始链表的节点和拷贝的节点放入哈希表；再遍历一遍，根据哈希表中原始节点的信息，来把新节点之间的边画出来。
class Solution {
    Map<Node,Node>map=new HashMap<>();
    public Node copyRandomList(Node head) {
        Node cur=head;
        while(cur!=null){
            map.put(cur,new Node(cur.val));
            cur=cur.next;
        }
        cur=head;
        while(cur!=null){
            map.get(cur).next=map.get(cur.next);
            map.get(cur).random=map.get(cur.random);
            cur=cur.next;
        }
        return map.get(head);
    }
}







