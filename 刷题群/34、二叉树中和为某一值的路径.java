这题关键是打印路径，比如遍历二叉树的话就很简单，但是打印所有从根节点到叶节点的路径，我就想了一两个小时也不会，我真是颓了，都开始用野对象去尝试了。
方法是：弄一个全局变量的list来保存路径，然后遍历，把节点加入list，如果是叶节点就打印list。然后调用自身（左子节点和右子节点），调用完后把加入list的节点删去。
      这就是所谓回溯，即调用各个方向的自身的之前和之后的代码，完成相反的动作（如加入和删除、标记和去标记等）。

一、先看下面这个最简单的打印二叉树所有路径。
class TreeNod {
    int val;
    TreeNod left;
    TreeNod right;
    TreeNod(int x) { val = x; }
}
class Ceshi {
    ArrayList<Integer> res=new ArrayList<>();
    public static void main(String[] args) {
        TreeNod head=new TreeNod(1);
        head.left=new TreeNod(2);
        head.right=new TreeNod(3);
        head.left.left=new TreeNod(4);
        head.left.right=new TreeNod(5);
        Ceshi ceshi=new Ceshi();
        if(head!=null) ceshi.recur(head);
    }
    void recur(TreeNod node){
        res.add(node.val);
        if(node.left==null &&node.right==null){
            for(int i=0;i<res.size();i++)System.out.println(res.get(i));
        }
        if(node.left!=null)recur(node.left);
        if(node.right!=null)recur(node.right);
        res.remove(res.size()-1);
    }
}

二、再看那道题。（注意可能有负数所以不能小于0就结束）
注意：把temp加入res时，不能直接加入，不然加入的是一个引用，之后该引用指向的对象会发生修改的，就乱套了。所以这里要新建一个副本new ArrayList<>(temp)
class Solution {
     List<List<Integer>> res=new ArrayList<>();
     List<Integer> temp=new ArrayList<>();
     public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root==null)return res;
        recur(root,sum);
        return res;
     }
     void recur(TreeNode root,int sum){
         temp.add(root.val);
         sum=sum-root.val;
         if(root.left==null&&root.right==null&&sum==0){
             res.add(new ArrayList<>(temp));
         }
         if(root.left!=null)recur(root.left,sum);
         if(root.right!=null)recur(root.right,sum);
         temp.remove(temp.size()-1);
     }
}

然后是把ArrayList改成LinkedList比较好一些，因为ArrayList底层是数组，需要扩容（开辟一块更大的空间然后整体拷贝），LinkedList就不用。
