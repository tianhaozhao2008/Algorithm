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

方法1：我的做法，是先二分，如果有重复值的话，那么查找到的位置就不一定是哪一个了，那就分别再向左和向右遍历，统计重复了几次。
时间复杂度O（logn + k） k是重复的次数。 空间复杂度O（1）。
class Solution {
    public int search(int[] nums, int target) {
        int left=0;
        int right=nums.length-1;
        int index=-1; //target的下标,默认是-1代表没有。
        while(left<=right){
            int mid=(left+right)/2;
            if(target==nums[mid]){
                index=mid;
                break;
            }
            if(target>nums[mid]) left=mid+1;
            else right=mid-1;
        }
        if(index==-1) return 0;
        int sum=1;
        for(int i=index+1;i<nums.length;i++){
            if(nums[i]==target)sum++;
            else break;
        }
        for(int i=index-1;i>=0;i--){
            if(nums[i]==target)sum++;
            else break;
        }
        return sum;
    }
}

方法2：题解的方法。也是先二分找到下标，然后此时不是遍历找重复次数，而是分别再用两次二分来找重复的数值的左右边界。
因此当重复次数特别多的时候比如重复了n次，那么上一种方法就退化成O（n）的时间复杂度了，而这种还是O（nlogn）。



