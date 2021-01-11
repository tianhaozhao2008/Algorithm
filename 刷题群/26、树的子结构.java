首先明确子结构跟子树的概念是不同的，只要B树是A树的一部分，就可以认为B是A的子结构，B不一定非要是A的子树。
这题就是考递归的：递归有返回值，所以每次都要return自身，那么return的左子树和右子树之间，是用&&连接还是||连接就要讲究了。
1、先写一个递归方法遍历A的所有节点。
2、再写一个递归，对于A的每个节点都再调用一个递归，判断A和B是否子结构。

class Solution {
    TreeNode B;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        this.B=B;
        if(B==null)return false;
        return dfs(A);
    }
    //dfs是判断这棵树是否包含B树的结构。
     boolean dfs(TreeNode x){
        if(x==null) return dfs2(x,B); //基本结束条件，如果已经遍历到最终的节点了，就直接返回是否有相同的。
        else if(dfs2(x,B)) return true; //判断这个节点开始是否与B树相同，相同则返回ture，不相同就判断下一个节点
        else return dfs(x.left) || dfs(x.right);  //只要有一个子树包含那个结构就返回true
    }
    //dfs2是判断两棵树的结构是否相同。
    boolean dfs2(TreeNode x,TreeNode b){
        if(b==null)return true;
        else if(x==null&&b!=null)return false;
        else if(x.val!=b.val)return false;
        else{
            return dfs2(x.left,b.left) && dfs2(x.right,b.right); //结构相同就必须左右子树都相同
        }
    }
}
