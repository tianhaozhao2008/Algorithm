方法1：
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

方法1改进：可以看到方法1是dfs，即所有节点的四个方向最终都要判断一遍，n个节点就要判断4n次，
但我们要的是如果一个方向能走下去，那么剩下的三个方向就不用走了（不用判断了），因此我们加上if来判断这个方向能不能走下去（第58行）。用时由2ms变成1ms，打败97%
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
        if(i<0||j<0||i>=matrix.length||j>=matrix[0].length||matrix[i][j]==1234) return;
        res[index]=matrix[i][j];
        matrix[i][j]=1234;
        index++;
        if(direction==1){
            //这里改的，原先是4个方向都调用一遍dfs，现在是如果有一个方向能走就不调用其它的dfs了。
            if(j+1<matrix[0].length &&matrix[i][j+1]!=1234) dfs(i,j+1,1); 
            else if(i+1<matrix.length&&matrix[i+1][j]!=1234)dfs(i+1,j,2);
            else if(j-1>=0 &&matrix[i][j-1]!=1234)dfs(i,j-1,3);
            else dfs(i-1,j,4);
        }
        else if(direction==2){
            if(i+1<matrix.length&&matrix[i+1][j]!=1234)dfs(i+1,j,2);
            else if(j-1>=0 &&matrix[i][j-1]!=1234)dfs(i,j-1,3);
            else if(i-1>=0 &&matrix[i-1][j]!=1234)dfs(i-1,j,4);
            else dfs(i,j+1,1);
        }
        else if(direction==3){
            if(j-1>=0 &&matrix[i][j-1]!=1234)dfs(i,j-1,3);
            else if(i-1>=0 &&matrix[i-1][j]!=1234)dfs(i-1,j,4);
            else if(j+1<matrix[0].length &&matrix[i][j+1]!=1234)dfs(i,j+1,1);
            else dfs(i+1,j,2);
        }
        else{
            if(i-1>=0 &&matrix[i-1][j]!=1234)dfs(i-1,j,4);
            else if(j+1<matrix[0].length &&matrix[i][j+1]!=1234)dfs(i,j+1,1);
            else if(i+1<matrix.length&&matrix[i+1][j]!=1234)dfs(i+1,j,2);
            else dfs(i,j-1,3);
        }
    }
}

方法2：官方方法，就是循环遍历。
其实这个方法简单，就是循环，设置四个指针：起始行rowStart、终止行rowEnd、起始列colStart、终止列colEnd，然后依次遍历一行一列一行一列
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length==0||matrix[0].length==0)return new int[]{};
        int []res=new int[matrix.length*matrix[0].length];
        int index=0;

        int rowStart = 0;
        int rowEnd = matrix.length - 1;
        int colStart = 0;
        int colEnd = matrix[0].length - 1;

        while(true) {
            for (int i = colStart; i <= colEnd; i++) {
                res[index] = matrix[rowStart][i];
                index++;
            }
            rowStart++;
            if (rowStart > rowEnd) break;
            
            for (int i = rowStart; i <= rowEnd; i++) {
                res[index] = matrix[i][colEnd];
                index++;
            }
            colEnd--;
            if(colEnd<colStart) break;
            
            for (int i = colEnd; i >= colStart; i--) {
                res[index] = matrix[rowEnd][i];
                index++;
            }
            rowEnd--;
            if(rowEnd<rowStart) break;
            
            for (int i = rowEnd; i >= rowStart; i--) {
                res[index] = matrix[i][colStart];
                index++;
            }
            colStart++;
            if(colStart>colEnd) break;
        }
    return res;
    }
}







