这题不难，就是从左往右数，如果和小于0了，那么就不要了，重新使sum归零。（考虑全是负数的情况，所以max初始值不设成0而设成nums[0]）
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length==0)return 0;
        int sum=0;
        int max=nums[0];
        for(int x:nums){
            sum=sum+x;
            if(sum>max) max=sum;
            if(sum<0) sum=0;
        }
        return max;
    }
}

然后看题解说是动态规划。
