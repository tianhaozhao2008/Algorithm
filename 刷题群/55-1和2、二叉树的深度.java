第一问很简单，时间复杂度是O（n），因为求深度要遍历整棵树。空间复杂度是O（n），就是看递归的深度，最差退化成链表。
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
