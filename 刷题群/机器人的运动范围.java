剑指 Offer 13. 机器人的运动范围  https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

即用回溯算法：深度优先或广度优先来做。

深度优先：深度优先的特征是递归参数是节点的横纵坐标，调用自身是分别向不同方向调用四次自己。
分为查找和遍历两种类型：
1.查找：上一道题。有返回值，每次调用自身前先把自己的值改成'/0'代表已经访问过，然后调用四次自身（||连接），调用完后再改回来自己的值。
2.遍历：我习惯是无返回值。调用自身是直接执行四次，基本结束条件是节点没有访问过。因此每次访问完节点后都要在一个集合或数组中记录。
class Solution {
    int m,n,k;
    int [][] resList;
    public int movingCount(int m, int n, int k) {
        this.m=m;this.n=n;this.k=k;
        this.resList=new int[m][n];
        dfs(0,0);
        int sum=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                sum+=resList[i][j];
            }
        }
        return sum;
    }
    void dfs(int i,int j){
        if(i>=0&&i<=m-1&&j>=0&&j<=n-1&&i%10+i/10+j%10+j/10<=k&&resList[i][j]==0 ){
            resList[i][j]=1;
            dfs(i+1,j);
            dfs(i-1,j);
            dfs(i,j+1);
            dfs(i,j-1);
        }
    }
}


广度优先：就是用一个队列（不用递归了），先把第一个节点入队，然后每次出队，检查符合要求则把上下左右的节点入队，直到队列为空。
不过这种广度优先似乎无法起到查找的功能，只能起到遍历的作用。
class Solution {
    public int movingCount(int m, int n, int k) {
        int [][] res=new int[m][n];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while(queue.size()!=0){
            int[] position=queue.poll();
            int i=position[0];
            int j=position[1];
            if(i<0||i>=m||j<0||j>=n||i/10+i%10+j/10+j%10>k||res[i][j]==1){
                continue;
            }
            res[i][j]=1;
            queue.offer(new int[]{i+1,j});
            queue.offer(new int[]{i-1,j});
            queue.offer(new int[]{i,j+1});
            queue.offer(new int[]{i,j-1});
        }
        int sum=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                sum+=res[i][j];
            }
        }
        return sum;
    }
}
