输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

这题有点难，首先你要知道对于二叉搜索树，如果你用中序遍历，那么就是从小到大的顺序遍历一遍，就很符合排序的链表
因此你只需要中序遍历的同时，调一下指针的指向，你需要把上一个节点的right指向当前节点，把当前节点的left指向上一个节点（由于当前节点的right没动，所以后面那个递归调用不会出错）。
我想了很久都不知道如何表示上一个节点，但其实看题解后很简单就是当前节点调好后，把当前节点cur设置成pre，然后下一次的时候当前节点cur和上次的pre就都有了。
class Solution {
    Node pre;
    Node head;
    public Node treeToDoublyList(Node root) {
        if(root==null)return null;
        recur(root);
        head.left=pre;
        pre.right=head;
        return head;
    }
    void recur(Node cur){
        if(cur==null)return;
        recur(cur.left);
        if(pre==null){
            pre=cur;
            head=cur;
        }
        else{ //这里如果不加else，那么第一个节点就会left和right指针都指向自己，然后后面的recur(cur.right)就无限循环。
            pre.right=cur;
            cur.left=pre; //注意这里修改了cur的left指针而没有修改right指针，所以等会recur(cur.right)是没问题的。
            pre=cur;
        }
        recur(cur.right);
    }
}
