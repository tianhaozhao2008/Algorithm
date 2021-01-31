这题首先要抽象出最近祖先的特征，这一步我没搞好所以后面代码比较难写。。
我找公共祖先的方式是如果当前这个节点可以走到那两个节点，但是当前节点的两个子节点不能同时走到那两个节点，那么当前节点就是祖先节点。

还需要进一步抽象：
这题给的是二叉搜索树，说明左边小右边大（由于不是平衡二叉树所以可能退化成链表，分析时间复杂度的时候考虑下）
然后说所有节点的值唯一，然后给定的两个节点均在这个二叉搜索树中，因此情况就简单了：
如果root是a和b的祖先节点，那么满足下面条件：
1.a和b一个比root小一个比root大（即分别在root的左子树和右子树中）
2.或者a和b中有一个的值等于root。（那么另一个一定在左子树或右子树中，因为是从上往下迭代遍历的，所以另外一个肯定不是在父节点以上）

因此迭代版本的代码如下：时间复杂度就是二叉树的层数，一般是O（logn），最差时退化成链表是O（n）。
以下代码为了提高健壮性，建议把true改成root!=null（这样是防止循环到root为空时报错，虽然题目不存在这种情况但是写while true怕被面试官
diss）。改了while的条件判断后，要再在外面加一个return不然编译器报错（认为可能无返回值）
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while(true){
            if(root.val>Math.min(p.val,q.val)&&root.val<Math.max(p.val,q.val)) return root;
            else if(root.val==p.val ||root.val==q.val) return root;
            else if(root.val<p.val &&root.val<q.val) root=root.right;
            else root=root.left;
        }
    }
}

下面这个是递归的版本。时间复杂度一样，空间复杂度是递归栈的深度，最深是O（n）。
递归和迭代的都要会写，比如之前的二分查找，我们就是用迭代写的（最早我是递归写的，但发现迭代的边界条件比较清晰）
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)return null;
        if(root.val>Math.min(p.val,q.val)&&root.val<Math.max(p.val,q.val)) return root;
        else if(root.val==p.val ||root.val==q.val) return root;
        else if(root.val<p.val &&root.val<q.val) return lowestCommonAncestor(root.right,p,q);
        else return lowestCommonAncestor(root.left,p,q);
    }
}
