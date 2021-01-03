剑指 Offer 07. 重建二叉树 https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/

输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
   
   解法：
   前序遍历是根节点、左子树、右子树。
   中序遍历是左子树、根节点、右子树。
   因此，前序遍历的第一个节点就是根节点，然后记录这个根节点的值，去中序遍历的输出数组中找到对应的值，把它分成左右子树。
   然后根据中序遍历输出数组中的左右子树，对应到前序遍历的输出数组，对应子树的第一个就是子树的根节点。
   一直重复以上步骤，每重复一次都能找到根节点和两个子树的根节点。具体算法通过递归实现，现在暂时先不想。
   具体题解参考https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/

最新总结：这种涉及到子问题类似的，就直接分治法递归，不用想什么循环。
1.先把递归的样子写出来：每次都把数组拆分成更小的数组，具体拆分的函数是Arrays.copyOfRange()，注意区间是左闭右开。
2.考虑边界条件｛最后只剩两个节点（只有左子树）、最后只剩两个节点（只有右子树）、最后只剩一个节点｝
3.考虑特殊输入值，如[][]，输入两个空数组。
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) { val = x; }
//}

class Solution {
//    public static void main(String[] args) {
//        Solution solution = new Solution();
//        TreeNode head = solution.buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
//        System.out.println(head.right.val);
//    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length==0){ //特殊输入值
            return null;
        }
        int fatherValue=preorder[0];
        TreeNode head=new TreeNode(fatherValue);
        if(preorder.length==1){  //边界条件
            return head;
        }
        int leftLength=findIndex(inorder,fatherValue); //左子树的节点数，也是第二个列表中头结点的下标
        if(1<leftLength+1){  ////边界条件
            head.left=buildTree(Arrays.copyOfRange(preorder,1,leftLength+1),
                                Arrays.copyOfRange(inorder,0,leftLength));
        }
        if(leftLength+1<preorder.length){ ////边界条件
            head.right=buildTree(Arrays.copyOfRange(preorder,leftLength+1,preorder.length),
                                 Arrays.copyOfRange(inorder,leftLength+1,inorder.length));
        }
        return head;
    }
    
    public int findIndex(int[]list,int num){
        for(int i=0;i<list.length;i++){
            if(list[i]==num){
                return i;
            }
        }
        return -1;
    }
}
   
   
   
