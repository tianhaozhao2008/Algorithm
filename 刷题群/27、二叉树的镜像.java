简单的递归，2分钟做完。

class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        mirror(root);
        return root;
    }
    void mirror(TreeNode root){
        if(root==null)return;
        TreeNode x = root.left;
        root.left=root.right;
        root.right=x;
        mirror(root.left);
        mirror(root.right);
    }
}
