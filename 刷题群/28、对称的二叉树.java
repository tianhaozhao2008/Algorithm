1.我首先想到的是先用刚才那题的翻转二叉树，然后再遍历两个二叉树看各个节点的值，缺点是空间复杂度多一倍，时间复杂度多遍历一遍。
而且如果直接调用翻转二叉树的方法的话，会在那个二叉树基础上修改，改了之后两个引用都会指向相同的对象，
所以还得用深拷贝一个对象，但该节点类没提供拷贝对象的方法，就很烦。除非翻转二叉树的方法返回的是一个新的二叉树而不是在原始二叉树上修改。。


2.用bfs实现：先把root的两个子节点入队，然后出队——关键点是每次出队两个元素（我想到了bfs但是没想到要两个一起出队。这样才能继续再入队4个元素，才能使两两对称比较）
这种方法运行较慢，bfs的队列和dfs的栈的出入容器次数是一样的，速度差别的原因可能jvm栈操作与LinkedList速度不同，以及涉及到内存寻址，局部性原理。
bfs占的空间多应该是队列中存放的是节点对象的引用，而dfs的栈中存放的应当是代码块。
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(queue.size()>=2){
            TreeNode a=queue.poll();
            TreeNode b=queue.poll();
            if(a==null &&b==null) continue;
            else if(a==null||b==null) return false;
            else if(a.val==b.val){
                queue.offer(a.left);
                queue.offer(b.right);
                queue.offer(a.right);
                queue.offer(b.left);
            }
            else return false;
        }
        if(queue.size()==0) return true;
        else return false;
    }
}

3.分别对head节点的两个子树做dfs遍历（前序遍历），只不过一个是中左右，一个是中右左。（或者后续遍历也ok）
