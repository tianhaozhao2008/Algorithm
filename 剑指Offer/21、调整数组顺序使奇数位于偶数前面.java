输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。

总结：就是快速排序的那个左右游标。
关键点是注意特殊情况（这题tm的提交了好几次才过）：
1、已经排序好的数组如1，3，4
2.全是奇数或全是偶数的数组：如1，3，5

class Solution {
    public int[] exchange(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while(left<right){ //下面这个判断是防止全是奇数时越界。
            while (nums[left]%2==1 &&left<nums.length-1){
                left++;
            }
            while(nums[right]%2==0 &&right>0){
                right--;
            }
            if(left>=right){break;} //防止已经排序好的特殊情况，如1，3，4，此时left会移动到right右边
            int mid=nums[left];
            nums[left]=nums[right];
            nums[right]=mid;
            left++;
            right--;
        }
        return nums;
    }
}
