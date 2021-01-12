我想到的是dfs，然后递归参数除了坐标外，还加上控制的方向。
遇到的问题是对于访问过的元素，不知道用什么标记（如果是char类型的话可以直接用'\0'代表空，int类型的话如果'\0'就直接是0了，如果随便写个数-2323来标记，绝对被面试官diss）
如果再弄个数组来标记的话，占用空间复杂度了。群里告诉我用Integer数组来标记，然后放个null进去。关于如何int数组转换成Integer数组以后再看，这里先不讲武德了。

class Solution {
    int[][]matrix;
    int[]res;
    int index=0;

    public int[] spiralOrder(int[][] matrix) {
        this.matrix=matrix;
        if(matrix.length==0 || matrix[0].length==0)return new int[]{};
        res=new int[matrix.length*matrix[0].length];
        dfs(0,0,1);
        return res;
    }
    void dfs(int i,int j,int direction){ //优先搜索的方向。1是右，2是下，3是左，4是上
        if(i<0||j<0||i>=matrix.length||j>=matrix[0].length||matrix[i][j]==-1111) return;
        res[index]=matrix[i][j];
        matrix[i][j]=-1111;
        index++;
        if(direction==1){
            dfs(i,j+1,1);dfs(i+1,j,2);dfs(i,j-1,3);dfs(i-1,j,4);
        }
        else if(direction==2){
            dfs(i+1,j,2);dfs(i,j-1,3);dfs(i-1,j,4);dfs(i,j+1,1);
        }
        else if(direction==3){
            dfs(i,j-1,3);dfs(i-1,j,4);dfs(i,j+1,1);dfs(i+1,j,2);
        }
        else{
            dfs(i-1,j,4);dfs(i,j+1,1);dfs(i+1,j,2);dfs(i,j-1,3);
        }
    }
}
