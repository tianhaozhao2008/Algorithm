从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层单独打印到一个数组，结果就是一个二维数组。

思路：一开始觉得挺难，想了一会发现很简单。就是每次不是弹一个了，而是弹出来当前的size()个，
然后把这些的值保存到一个ArrayList,然后每while循环一次都把这个动态数组添加到大数组。

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)return res;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        while(queue.size()!=0){
            int size= queue.size();
            List<Integer>midRes=new ArrayList<>();
            for(int i=1;i<=size;i++){
                TreeNode node=queue.poll();
                midRes.add(node.val);
                if(node.left!=null) queue.offer(node.left);
                if(node.right!=null) queue.offer(node.right);
            }
            res.add(midRes);
        }
        return res;
    }
}





