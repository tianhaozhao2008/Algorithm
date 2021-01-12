从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

就是广度优先用队列做。
注意点：Queue中，放入的null元素也会占用一格长度，所以当弹出null元素取val就会报错，因此不要放入null元素。
class Solution {
    public int[] levelOrder(TreeNode root) {
        ArrayList<Integer>list=new ArrayList<>();
        Queue<TreeNode>queue=new LinkedList<>();
        if(root==null)return new int[]{};
        queue.offer(root);
        while(queue.size()!=0){
            TreeNode node=queue.poll();
            list.add(node.val);
            if(node.left!=null)queue.offer(node.left);
            if(node.right!=null)queue.offer(node.right);
        }
        int[]res=new int[list.size()];
        for(int i=0;i<list.size();i++){
            res[i]=list.get(i);
        }
        return res;
    }
}
