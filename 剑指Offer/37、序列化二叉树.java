（这题通法就是记住层次遍历bfs序列化，然后反序列化时也是用栈，也是第一个节点入栈，然后弹栈，建立左右子节点（根据序列化列表中的一个递增的指针），并把左右子节点入栈）

序列化就是把二叉树层次遍历（bfs）的节点值保存成字符串如“[1,2,3,null,null,4,5]”
反序列化就是把刚才那个字符串，重建二叉树。

1.对于序列化：就是直接用队列做bfs，然后把节点的值保存成字符串。（一些细节如用StringBuild类、字符串的substring（a，b）方法、split（“，”）方法、字符串比较用equal方法、
String.valueOf方法、toString方法等等）

2.对于反序列化：就是根据字符串重建二叉树，首先要切掉字符串的[和]，然后以逗号为分隔把它分隔成字符串数组。
接下来有两种方法：
（1）一种是观察规律，即第i个位置的节点的左子树在第几个位置、右子树在第几个位置（一定是左子树的位置+1，因为每次都是连着放入两个）。
可以想到当前节点的左右子树在哪个位置还与当前节点之前的null节点的数量有关，而且是负相关。
总之就是当前节点的位置多大，左右子树位置越大；当前节点之前的null节点越多，左右子树位置越小。
因此不难发现规律：设当前节点位置是i，当前节点之前的null的个数是m，那么左子树就是2*（i-m）+1，右子树就是2*（i-m）+2.
  那么，我们首先弄一个数组，记录各个位置之前有几个null；
        然后再弄一个数组，保存所有new 出来的新节点（此时还没有建立左右指针）
         然后遍历刚才新建的节点数组，根据规律构建指针。  （这种方法是新建了两个数组所以空间复杂度是O（2N））

（2）不需要找规律，就是弄一个队列，先把第一个节点放进去，
然后取出第一个节点，把第一个节点的left指向序列化数组的1号位置的节点（要new一个）并把它入队，把第一个节点的right指向序列化数组的2号位置并把它入队。。
一直循环，不断使序列化数组中的指针+1，并出队入队，直到队列为空。
方法1：（题解里介绍了两种方法但是它的代码是方法二的即直接用队列，所以我把方法1写了一遍。注意字符串比较不能用==）
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
        codec.deserialize(x);
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
        if(data.equals("[]"))return null;
        String []res=data.substring(1,data.length()-1).split(",");
        int [] howManyNull=new int[res.length]; //记录各个位置有多少个空格
        int sum=0;
        for(int i=0;i<res.length;i++){
            if(res[i].equals("null"))sum++;
            howManyNull[i]=sum;
        }
        TreeNode[]nodeList=new TreeNode[res.length]; //承装所有节点，都先初始化一遍
        for(int i=0;i<res.length;i++){
            if(!res[i].equals("null")){
                nodeList[i]=new TreeNode(Integer.valueOf(res[i]));
            }
        }
        //接下来在nodeList列表中把各个节点的边连上
        for(int i=0;i<res.length;i++){
            if(nodeList[i]==null)continue;
            if(res[2*(i-howManyNull[i])+1]!=null) nodeList[i].left=nodeList[2*(i-howManyNull[i])+1];
            if(res[2*(i-howManyNull[i])+2]!=null) nodeList[i].right=nodeList[2*(i-howManyNull[i])+2];
        }
        return nodeList[0];
   }
}


方法2：（这里只写反序列化的方法了）反序列化也是用队列，直接copy的题解。
 public TreeNode deserialize(String data) {
        if(data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
