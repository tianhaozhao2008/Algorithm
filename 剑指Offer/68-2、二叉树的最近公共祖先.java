方法1：这题是相对于上一题，把二叉搜索树换成普通二叉树了。还是如果p和q分别在左右子树或pq中有一个等于当前节点，那么当前的root就是祖先借点。
我的思路就是如果当前节点的值等于p或q，那么直接return当前节点；否则，就搜索当前节点的左子树是否包含p或q，右子树是否包含p或q，
如果p和q分别在左右子树，就return当前节点；如果都在同一个子树，就递归调用那个子树。
我这个思路时间复杂度在好的情况是O（n），即如果是平衡的树，那么第一次是搜所有节点即n次，下降一次是搜n/2次，再下降是n/4次。。加起来就是O（n）；
如果最差情况退化成链表，时间复杂度就是O（n*n）。 空间复杂度：最差情况退化成链表，就是O（n）。

关于空间复杂度这里多说一下，之前的快速排序，结尾是调用两次自身，即两个quickSort(arr, right + 1, rightIndex)，没有返回值，所以递归栈的深度
取决于这两个排序的部分的大小，如果先递归调用的那部分长度很小，每次都是1，那么整个递归栈的深度就是O（1）；如果先递归调用的长度很大，那么每次递归
时压栈后，新的递归一次搞不定继续压栈，那么递归栈就很深，是O（n）。
而这里的递归栈，由于有return返回值，所以每次调用递归必须压栈，因为当前的函数还没有返回，即没有执行完，当计算完调用自身的那个函数结果后，再把当前
的弹栈，然后return那个结果，所以空间复杂度最深就是O（n）。如果是无返回值的话，那么如果最后一条语句是调用自身，那么此时就不用压栈（即对应快排的那
种情况）。
时间复杂度O（n*n），空间复杂度O（n）。
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val==p.val||root.val==q.val)return root;
        boolean leftP=ifContainNode(root.left,p.val);
        boolean leftQ=ifContainNode(root.left,q.val);
        boolean rightP=ifContainNode(root.right,p.val);
        boolean rightQ=ifContainNode(root.right,q.val);
        if((leftP&&rightQ)||(rightP&&leftQ)) return root;
        else if(leftP &&leftQ) return lowestCommonAncestor(root.left,p,q);
        else if(rightP&&rightQ) return lowestCommonAncestor(root.right,p,q);
        else return null;
    }
    boolean ifContainNode(TreeNode root,int x){
        if(root==null) return false;
        if(root.val==x) return true;
        return ifContainNode(root.left,x) || ifContainNode(root.right,x);
    }
}

方法2：时间复杂度优化成O（n）即指遍历一遍。空间复杂度还是O（n）即递归栈的深度。
思路还是如果root是p和q的祖先节点，要么p和q分别在root的左右子树，要么root==p或q。

然后递归地去做，基本结束条件就是root==p或q，如果不是，就看左子树和右子树中是否包含p或q，如果左右子树返回的都不是空，
说明p和q分别在左右子树所以return root，如果左右子树的返回值一个空一个不空，那就说明在返回不空的那个子树中找到了祖先节点，
直接返回那个即可
（关键：因为返回上来的那个就是祖先节点，原因是一个节点被返回，要么是它的左右子树分别含有p和q，要么是它等于p或q，这恰恰就是祖先节点）

这个递归其实蛮绕的，基本结束条件其实有两个：
1、root==p或q，这个很容易发现；
2、还有一个容易忽视的，就是调用自身看左子树和右子树是否包含p或q，如果包含则return root。（在这里调用的自身函数
视作“子树是否包含p或q”）；而当只有一个子树返回的不是null时，这时的调用自身的函数又视作“子树是否包含祖先节点”。
~画画图看看运行的过程就更加清晰了~

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)return null;
        if(root.val==p.val||root.val==q.val)return root;
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null &&right!=null)return root;
        else if(left!=null && right==null) return left;
        else if(left==null && right!=null) return right;
        else return null;
    }
}
