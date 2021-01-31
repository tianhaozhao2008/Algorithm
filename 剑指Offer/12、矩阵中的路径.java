剑指 Offer 12. 矩阵中的路径  https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/

请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
[["a","b","c","e"],
["s","f","c","s"],
["a","d","e","e"]]

但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。

示例 1：
输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
输出：true

示例 2：
输入：board = [["a","b"],["c","d"]], word = "abcd"
输出：false

就是深度优先，分别对于二维数组的每个点，都执行深度优先的dfs方法，直到搜到就return。
对于dfs方法，先把当前值改了（比如改成空'\0'）,然后分别对上下左右四个方向调用自身，结束后再把当前值改回来。
注意基本结束条件（越界或者没有匹配上）
class Solution {
    char[][]board;
    char[]words;
    public boolean exist(char[][] board, String word) {
        this.board=board;
        words=word.toCharArray();
        for(int i=0;i< board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    boolean dfs(int i,int j,int n){
        if(i<0 || j<0 || i> board.length-1 || j>board[0].length-1 || board[i][j]!=words[n]){
            return false;
        }
        //接下来的情况就是没有越界且匹配上了那个字符：
        if(n==words.length-1){
            return true;
        }
        board[i][j]= '\0';
        boolean res=dfs(i+1,j,n+1)||dfs(i-1,j,n+1) ||
                dfs(i,j+1,n+1)||dfs(i,j-1,n+1);
        board[i][j]=words[n];
        return res;
    }
}

