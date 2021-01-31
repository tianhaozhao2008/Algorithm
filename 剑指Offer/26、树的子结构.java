首先明确子结构跟子树的概念是不同的，只要B树是A树的一部分，就可以认为B是A的子结构，B不一定非要是A的子树。
这题就是考递归的：递归有返回值，所以每次都要return自身，那么return的左子树和右子树之间，是用&&连接还是||连接就要讲究了。
1、先写一个递归方法遍历A的所有节点。
2、再写一个递归，对于A的每个节点都再调用一个递归，判断A和B是否子结构。

return处用||还是&&，要看该递归函数的意义，比如如果是判断结构是否完全一样，那就要求左子树和右子树都一样，自然要用&&连接；
如果该递归函数的意义是遍历所有节点只要有一个节点符合要求就行，那么就是左右子树中只要有一个包含符合要求的就可以，所以用||连接。

基本结束条件的返回值如何写：就当做是最简单的情况（关注的是“最简情况”——即你可以当做现在这棵树只有一个节点，然后根据实际意义判断此时应该返回什么。

class Solution {
    TreeNode B;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        this.B=B;
        if(B==null)return false;
        return dfs(A);
    }
    //dfs是判断B是否是A的子结构。
     boolean dfs(TreeNode x){
        if(x==null) return dfs2(x,B); //基本结束条件，如果已经遍历到最终的节点了，就直接返回是否有相同的。
        else if(dfs2(x,B)) return true; //判断这个节点开始是否与B树相同，相同则返回ture，不相同就判断下一个节点
        else return dfs(x.left) || dfs(x.right);  //只要有一个子树包含那个结构就返回true
    }
    //dfs2是判断B是否与某个树完全相同，或者B是某个树开头的一部分。
    boolean dfs2(TreeNode x,TreeNode b){
        if(b==null)return true;
        else if(x==null&&b!=null)return false;
        else if(x.val!=b.val)return false;
        else{
            return dfs2(x.left,b.left) && dfs2(x.right,b.right); //结构相同就必须左右子树都相同
        }
    }
}
