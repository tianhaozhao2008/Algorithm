剑指 Offer 13. 机器人的运动范围  https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/

可以用深度优先或广度优先来做。这题不知道为啥dfs比bfs广度优先快很多（dfs是0~2ms，bfs是7ms），理论上讲它们的时间复杂度是一样的啊
后来在群里问了一下，说是dfs和bfs一个是压栈弹栈，一个是出队入队，出入容器的次数是一样的，可能是java的LinkList的出队入队的速度比jvm的栈的操作慢？
还有就是可能涉及到内存层面的局部性原理，栈的话操作热点在栈顶，而队列就比较分散了。

对于局部性原理（这个有时间再来看）：比如链表和数组，数组是使用的是连续的内存空间，可以利用空间局部性原理，借助 CPU cache进行预读，所以访问效率更高。
而链表不是连续存储，无法进行缓存，随机访问效率也较低。
数组空间不够了，涉及到扩容（即把原来的整个拷贝一份到更大范围的内存），就很耗时；链表的话要存指针，占的空间多一点点。
链表的话，如果对链表频繁的插入、删除操作会导致频繁的内存申请和释放


一、深度优先：深度优先的特征是递归参数是节点的横纵坐标，调用自身是分别向不同方向调用四次自己。
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


二、广度优先：就是用一个队列（不用递归了），先把第一个节点入队，然后每次出队，检查符合要求则把上下左右的节点入队，直到队列为空。
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
