这题用到二分查找，先回顾一下二分查找吧。。感觉太久没写、边界条件啥的可能一次写不对，要做到像归并、快排那么熟悉一次无bug写好。
二分查找用迭代吧，因为用递归的话边界条件不好想，怕出错。

很简单，就是一条while循环+三条判断。参数与归并、快排的一样，即数组和左右下标，区别是加上了要查找的那个数值。
while循环是看left是否小于等于right，不是就返回找不到；
第一个if是如果查找的等于中间的，等于则返回。
第二个if是如果查找的比中间的大，那么left就等于中间的加1；
第三个if是如果查找的比中间的小，那么right就等于中间的减1.

public class Solution {
    int search(int[]num,int x,int left,int right){
        while(left<=right){
            int mid=(left+right)/2;
            if(num[mid]==x)return mid;
            if(x>num[mid]) left=mid+1;
            else right=mid-1;
        }
        return -1;
    }
 }
