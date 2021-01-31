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

然后看题解说是动态规划。关键是要想出状态是如何定义的，比如dp[i]代表什么，我一开始是把它作为前i个元素中的连续子数组最大和，后来写出的状态转移函数一直不对。
看了题解与测试后，发现是比如前3个的和是100，中间有个-200，然后第i个数是50，那么就不能写成dp[i]=dp[i-1]+num[i]了，因为num[i-1]这块是断了的，
状态dp[i]应该定义成必须以第i个元素结尾的数组的最大值，那么dp[i]=dp[i-1]+num[i] if(dp[i-1]>0)   esle  dp[i]=num[i]
然后遍历整个dp数组，找到最大值即可。  
代码如下：对dp简化了，只用两个变量表示状态，然后用max变量记录最大值。
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length==0)return 0;
        int a=nums[0];
        int max=a;
        int b;
        for(int i=1;i<nums.length;i++){
            if(a<0) b=nums[i];
            else b=a+nums[i];
            a=b;
            if(a>max) max=a;
        }
        return max;
    }
}

