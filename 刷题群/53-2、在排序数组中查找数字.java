这题还是二分查找，查找缺失的数字，正常情况下n号下标的数字就是n，
1.如果n号下标的数字不是n，但n-1号下标的数字是n-1（或者n已经是最左边的left了），那么n号下标就是查到的了。
2.如果n号下标的数字不是n，n-1号下标的数字也不是n-1，那么就需要从左边去找，令right=mid-1
3.如果n号下标的数字是n，那么就需要从右边去找，令left=mid+1
下面这一条是我漏了的：如果没找到，说明比如数组长度是4，然后四个下标0。1，2，3分别就对应0，1，2，3，那么显然缺少的数字就是数组长度4.
class Solution {
    public int missingNumber(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=(left+right)/2;
            if(nums[mid]!=mid){
                if(mid==left ||nums[mid-1]==mid-1) return mid;
                else right=mid-1;
            }
            else left=mid+1;
        }
        return nums.length;
    }
}
