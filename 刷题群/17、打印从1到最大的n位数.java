剑指 Offer 17. 打印从1到最大的n位数 https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

示例 1:
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]

解析：这题很简单，返回的是数组，直接弄即可。
class Solution {
    public int[] printNumbers(int n) {
        int max= (int) (Math.pow(10,n)-1);
        int []res=new int[max];
        for(int i=0;i<res.length;i++){
            res[i]=i+1;
        }
        return res;
    }
}
但看题解说并不简单，面试的话需要考虑大数越界，要你把大数转换成字符串：
但是字符串是很难比较大小和按照数值顺序排的，所以采用一种分治的算法来生成，类似树的遍历那样，具体参考题解。
