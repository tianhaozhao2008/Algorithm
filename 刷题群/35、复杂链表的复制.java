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

方法3：题解中的第二种做法，不需要用哈希表了，时间复杂度变成O（1），但是这种修改了原始链表了（后来发现它又该回去了，所以，还ok。）。
就是
1.先对原始列表每个节点之后都连接一份自己的拷贝，如原来的a-b-c变成a-a'-b-b'-c-c'
2.根据上一个节点的random指针指向，填写下一个节点的random指针指向。（注意判断没有random指针的情况，防止null对象next时出bug）
3.删掉原始的节点，留下新的节点（注意原始的链表也要恢复，注意原始链表最后一个节点的指针指向要单独调）

class Solution {
    public Node copyRandomList(Node head) {
        if(head==null)return null;
        Node cur=head;
        //拷贝一份，即原先abc变成aa'bb'cc'
        while(cur!=null){
            Node tmp=cur.next;
            cur.next=new Node(cur.val);
            cur.next.next=tmp;
            cur=tmp;
        }
        cur=head;
        //根据前一个节点，做出拷贝的节点的random指针
        while(cur!=null){
            if(cur.random!=null) cur.next.random=cur.random.next;
            cur=cur.next.next;
        }
        //删除原始的节点（注意为了不修改原链表，所以对原链表也要恢复原样。注意先改原始链表的指针再改新链表的指针，不然会出错）
        cur=head.next;
        Node pre=head;
        Node res=head.next;
        while(cur.next!=null){
            pre.next=pre.next.next;
            cur.next=cur.next.next;
            cur=cur.next;
            pre=pre.next;
        }
        pre.next=null;//这里要单独处理原链表的最后一个节点，否则它是指向新链表的最后节点。应该把它改成指向null
        return res;
    }
}





