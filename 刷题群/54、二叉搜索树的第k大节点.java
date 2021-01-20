首先区分二叉搜索树和平衡二叉树，区别就是二叉搜索树可能是不平衡的，即退化成链表（这影响接下来的时间空间复杂度的分析）
搜索树的中序遍历（右子树、当前、左子树）就是从大到小的顺序，因此第k大的数，就每次遍历一遍k就减1，当k=0就说明是第k大了就记录。
之后再遇到k==0了就直接return（作为基本结束条件），就不再遍历了。（这个return如果放到了中间也ok，画画图看看）

时间空间复杂度的分析，这种遍历树的，由于遍历到最大值那么就是最右下角，遍历的次数就是树的高度，递归栈的深度也是树的高度。
由于是二叉搜索树而不是平衡树，所以可能退化成链表，所以时间和空间复杂度都是O（n）级别的。
class Solution {
     int k;
     int val;
    public int kthLargest(TreeNode root, int k) {
        this.k=k;
        dfs(root);
        return val;
    }
    void dfs(TreeNode root){
        if(k==0) return;
        if(root==null)return;
        dfs(root.right);
        k--;
        if(k==0) {
            val=root.val;
        }
        dfs(root.left);
    }
}
