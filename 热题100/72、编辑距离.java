就是简单的动态规划。状态转移函数就是：第一个单词的新增一个相当于第二个单词的删除一个，所以新增和删除是一种状态。
那么就三种状态：第一个单词删除一个、第二个单词删除一个、两个不删直接比
生信第一节课那个也类似。

class Solution {
    public int minDistance(String word1, String word2) {
        int[][]dp=new int[word1.length()+1][word2.length()+1];
        dp[0][0]=0;
        for(int i=1;i<dp.length;i++) dp[i][0]=dp[i-1][0]+1;
        for(int i=1;i<dp[0].length;i++) dp[0][i]=dp[0][i-1]+1;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=Math.min(Math.min(1+dp[i-1][j],1+dp[i][j-1]),dp[i-1][j-1]);
                }
                else{
                    dp[i][j]=Math.min(Math.min(1+dp[i-1][j],1+dp[i][j-1]),dp[i-1][j-1]+1);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}

如下是生信第一节课那个，动态规划填好表格（相符就加2分，移动了位置或者不相符就扣1分）后，
打印出路径（从头dfs搜索，同时标记回溯）
import java.util.*;

class Solution{
    String str1;
    String str2;
    List<String> temp=new ArrayList<>();
    Solution(String str1,String str2){
        this.str1=str1;
        this.str2=str2;
    }

    public static void main(String[] args) {
        String str1="TACGGGTAT";
        String str2="GGACGTAGC";
        Solution solution=new Solution(str1,str2);
        int[][]dp=solution.contScore();
//        for(int i=0;i<dp.length;i++){  //打印出整个dp表格
//            System.out.println(Arrays.toString(dp[i]));
//        }
        solution.dfs(dp,0,0);
    }

    int[][] contScore(){
        int[][]dp=new int[str1.length()+1][str2.length()+1];
        dp[0][0]=0;
        for(int i=1;i<dp.length;i++) dp[i][0]=dp[i-1][0]-1;
        for(int i=1;i<dp[0].length;i++) dp[0][i]=dp[0][i-1]-1;
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=Math.max(Math.max(dp[i-1][j]-1,dp[i][j-1]-1),dp[i-1][j-1]+2);
                }
                else{
                    dp[i][j]=Math.max(Math.max(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])-1;
                }
            }
        }
        System.out.println("最终的得分是"+dp[dp.length-1][dp[0].length-1]);
        return dp;
    }

    void dfs(int[][]dp,int i,int j){
        temp.add("("+j+","+i+")");
        //如果已经到最后一个了则打印路径
        if(i==dp.length-1&&j==dp.length-1){
            System.out.println(temp);
        }
        //向三个方向做dfs
        if(i+1<dp.length&&j<dp[0].length&&dp[i+1][j]==dp[i][j]-1){
            dfs(dp,i+1,j);
        }
        if(i<dp.length&&j+1<dp[0].length&&dp[i][j+1]==dp[i][j]-1){
            dfs(dp,i,j+1);
        }
        if(i+1<dp.length&&j+1<dp[0].length){
            if(str1.charAt(i)==str2.charAt(j)&&dp[i+1][j+1]==dp[i][j]+2){
                dfs(dp,i+1,j+1);
            }
            if(str1.charAt(i)!=str2.charAt(j) && dp[i+1][j+1]==dp[i][j]-1){
                dfs(dp,i+1,j+1);
            }
        }
        temp.remove(temp.size()-1);
        return;
    }
}
