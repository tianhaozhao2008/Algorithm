第一问很简单，时间复杂度是O（n），因为求深度要遍历整棵树。空间复杂度是O（n），就是看递归的深度，最差退化成链表。
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}

第二问：
方法1:就是沿用第一问的方法，遍历树的所有节点，对于每个节点都调用一次第一问中的方法来判断这个节点的左右子树高度是否平衡，
只要有一个高度不平衡，那么就停止（如果是循环的话我们可以用break，但是这里没有循环，所以对于这种无返回值的递归，我们需要
弄一个全局变量，然后每次递归开始前得基本结束条件都要检查一下全局变量的值，改变了的话就直接结束）。

复杂度分析:对于时间复杂度，当是二叉树即所有节点都遍历完的话，一共n个节点，每个节点都判断一次左右子树的高度（即遍历一遍左右子树），
但是不同节点的左右子树的节点数量是不同的，所以要分开来算。
第一层是1个节点，每个节点的左右子树和有n个节点。
第二层是2个节点，每个节点的左右子树和有n/2个节点。
第三层是4个节点，每个节点的左右子树和有n/4个节点。
。。。即每层的复杂度都是N，一共logN层，那么整体的时间复杂度就是O（nlogn）。
空间复杂度就是当退化成链表时，递归栈最深时是O（n）。

（写法1，无返回值的方法，这种如果中间一旦出现不符的想要break整个递归的话，就在基本结束条件加上一个判断全局变量的值的操作）
我感觉写法1虚浮一点？想要break递归的，都能成无返回值+全局变量感觉就很舒服。
class Solution {
    boolean res=true; 
    public boolean isBalanced(TreeNode root) {
        dfs(root);
        return res;      
    }
    void dfs(TreeNode root){
        if(root==null)return;
        if(res==false) return;
        int a=maxDepth(root.left);
        int b=maxDepth(root.right);
        if(Math.abs(a-b)>1) res=false;
        dfs(root.right);
        dfs(root.left);
    }
    
    int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
（写法2，就直接用带返回值的那个方法，注意如果当前已经不符合的话，就不递归调用之后的了。）
class Solution {
    boolean res=true;
    public boolean isBalanced(TreeNode root) {
        if(root==null)return true;
        int a=maxDepth(root.left);
        int b=maxDepth(root.right);
        if(Math.abs(a-b)>1)return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    int maxDepth(TreeNode root) {
        if(root==null)return 0;
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
    }
}
