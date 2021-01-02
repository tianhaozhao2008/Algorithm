剑指 Offer 04. 二维数组中的查找https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:
现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。
给定 target = 20，返回 false。

1.除了暴力外，可以对每一行使用二分查找。我首先想到的是根据行和列的大小关系，缩小检索的范围，把大矩阵切成一个小矩阵。
比如给定了N，
先看第一行，找到大于N的那个数的位置作为二分查找的end游标，
再看第一列，找到大于N的那个数的位置作为二分查找的最后一次所在的行。
不过这种复杂度还是nlogm 的。

2.题解中很刁钻的做法，就是观察这个矩阵可以发现规律：
随便选一个位置，向右或向下走，会变大；向左或向上走，会变小。
所以解法就是从右上角开始（左下角开始也可以）：如果N比这个位置的数大，就向下走，否则就向左走。
复杂度是m+n的。。
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0 || matrix[0].length==0){
            return false;
        }
        int high=matrix.length-1;
        int wide=0;
        int answer=matrix[high][wide];
        while(answer!=target){
            if(answer>target){
                if(high==0){
                    return false;
                }
                high--;
                answer=matrix[high][wide];
            }
            else{
                if(wide==matrix[0].length-1){
                    return false;
                }
                wide++;
                answer=matrix[high][wide];
            }
        }
        return true;
    }
}
