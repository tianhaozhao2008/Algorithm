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
   
   
   
   
