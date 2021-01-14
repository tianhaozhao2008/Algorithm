import com.sun.source.tree.Tree;

import java.util.*;


  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

public class Solution {

    public static void main(String[] args) {
        Solution codec=new Solution();
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.right.left=new TreeNode(4);
        root.right.right=new TreeNode(5);
        String x=codec.serialize(root);
        System.out.println(x);
    }
    public String serialize(TreeNode root) {
        if(root==null)return "[]";
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(root);
        StringBuilder res=new StringBuilder();
        res.append('[');
        while(queue.size()!=0){
            TreeNode temp=queue.poll();
            if(temp!=null)res.append(String.valueOf(temp.val)+',');
            else res.append("null,");
            if(temp!=null){
                queue.offer(temp.left);
                queue.offer(temp.right);
            }
        }
        res.deleteCharAt(res.length()-1);
        res.append(']');
        return res.toString();
    }


    public TreeNode deserialize(String data) {
        String []res=data.substring(1,data.length()-1).split(",");
        int [] howManyNull=new int[res.length]; //记录各个位置有多少个空格
        int sum=0;
        for(int i=0;i<res.length;i++){
            if(res[i]=="null")sum++;
            howManyNull[i]=sum;
        }
        TreeNode[]nodeList=new TreeNode[res.length]; //承装所有节点，都先初始化一遍
        for(int i=0;i<res.length;i++){
            if(res[i]!="null"){
                nodeList[i]=new TreeNode(Integer.valueOf(res[i]));
            }
        }
        //接下来在nodeList列表中把各个节点的边连上
        for(int i=0;i<res.length;i++){
            if(res[2*(i-howManyNull[i])+1]!=null) nodeList[i].left=nodeList[2*(i-howManyNull[i])+1];
            if(res[2*(i-howManyNull[i])+2]!=null) nodeList[i].right=nodeList[2*(i-howManyNull[i])+2];
        }
        return nodeList[0];
   }
}
