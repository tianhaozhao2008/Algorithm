方法1：我想的动态规划。dp[n]代表第n天卖出所能得到的最大利润。
显然，dp[n]=dp[n-1]+prices[n]-prices[n-1]  如果算出来的dp[n]是负的，那么dp[n]就设成0。
时间O（n），空间O（1）。
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int max=0;
        int dp=0;
        for(int i=1;i<prices.length;i++){
            dp=dp+prices[i]-prices[i-1];
            if(dp<0)dp=0;
            if(dp>max) max=dp;
        }
        return max;
    }
}

方法2：就是正向遍历，看第几天卖出的话，利润最大（要用一个变量保存之前出现过的最小的元素，代表那一天买）。本质上和方法1一样。
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int max=0;
        int get;
        int min=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<min) min=prices[i];
            get=prices[i]-min;
            if(get>max)max=get;
        }
        return max;
    }
}
