就是简单的动态规划。开辟一块m*n的数组用于记录结果：
class Solution {
    public int maxValue(int[][] grid) {
        if(grid.length==0 ||grid[0].length==0)return 0;
        int [][]dp = new int[grid.length][grid[0].length];
        dp[0][0]=grid[0][0];
        for(int i=1;i<dp[0].length;i++){
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for(int i=1;i<dp.length;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}

或者不开辟新的数组，直接在原数组上修改，省去m*n的空间复杂度，但是就改动了原始数据了。
class Solution {
    public int maxValue(int[][] grid) {
        if(grid.length==0 ||grid[0].length==0)return 0;
        for(int i=1;i<grid[0].length;i++){
            grid[0][i]=grid[0][i-1]+grid[0][i];
        }
        for(int i=1;i<grid.length;i++){
            grid[i][0]=grid[i-1][0]+grid[i][0];
        }
        for(int i=1;i<grid.length;i++){
            for(int j=1;j<grid[0].length;j++){
                grid[i][j]=Math.max(grid[i-1][j],grid[i][j-1])+grid[i][j];
            }
        }
        return grid[grid.length-1][grid[0].length-1];
    }
}
