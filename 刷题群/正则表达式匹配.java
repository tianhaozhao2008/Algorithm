剑指 Offer 19. 正则表达式匹配
总结：
1.两个字符串一般就用到动态规划，表格是二维数组，分别代表两个字符串的下标，
比如字符串abca和ab*，那么dp[4][3]就代表这两个字符串能否匹配上，
2.dp[4][3]这是最终情况，依赖于规模更小的情况比如dp[4-1][3]
那么关键点就是要找到状态转移函数，分情况讨论，正则表达式的最后一位是*或者不是*，是*的话可以匹配一个或不匹配。。。
3.从头填表、双层循环i和j（注意边界条件，比如i=0或j=0时，这块一般是根据直觉写一个然后用特殊示例来验证）
或者自顶向下的递归。

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];//实际多出来的[0]号位置用于填true。边界。
        f[0][0] = true;
        //边界条件：i！=0、j=0时肯定是false；而i=0时有可能是true比如j是a*时，所以要考虑i=0
        //这块边界很容易出错，所以先根据直觉写一个，然后带入几个特殊的简单实例测试一下。
        for (int i = 0; i <= m; i++) { //i和j代表字符串的第i 、j个元素
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') { //如果第j个元素是* （注意对应的下标是j-1）
                    if (matches(s, p, i, j - 1)) { //则看第j-1个元素是否匹配
                        f[i][j] = f[i][j-2] || f[i - 1][j];
                    }
                    else{
                        f[i][j] = f[i][j-2]
                    }
                }
                else {
                    if (matches(s, p, i, j)) { //字符串的第i个和第j个是否匹配
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i==0){ //之前考虑了i=0的情况，所以这里也补上（这是测试示例时发现的。。）
            return false;
        }
        if (p.charAt(j-1) == '.') {
            return true;
        }
        return s.charAt(i-1) == p.charAt(j-1);
    }
}


如下是递归的版本：一开始弄备忘录的时候，我是在main方法里初始化的数组，然后直接在另外那个方法里调用，发现用不了：
后来看其他的递归的题，发现是要在main方法里调用其它方法，把那个数组作为其他方法的参数传递过去，才可以用。。
（我这个递归是正常的，看很多题解的递归顺序是从字符串头部开始的，很奇怪，不看他们的了）
class Solution {
    public static void main(String[] args) {
        Solution result = new Solution();
        int [][] dict = new int[5][4];
        boolean output = result.isMatch(dict,"aaab","a*b",4,3);
        System.out.println(output);
    }
    public boolean isMatch(int [][] dict,String s, String p, int m, int n) { //求字符串的第m、n个元素
        if(dict[m][n]!=0){
            return dict[m][n] == 1;
        }
        if(m==0 && n==0){
            return true;
        }
        if(n==0 && m!=0){
            return false;
        }

        if (p.charAt(n - 1) == '*') {
            if (matches(s, p, m, n - 1)) {
                boolean answer=isMatch(dict,s, p, m - 1, n) || isMatch(dict,s, p, m, n - 2);
                if(answer){
                    dict[m][n]=1;
                }
                else{
                    dict[m][n]=2;
                }
                return answer;
            }
            else {
                boolean answer=isMatch(dict,s, p, m, n - 2);
                if(answer){
                    dict[m][n]=1;
                }
                else{
                    dict[m][n]=2;
                }
                return answer;
            }
        }
        else {
            if (matches(s, p, m, n)) {
                boolean answer= isMatch(dict,s, p, m - 1, n - 1);
                if(answer){
                    dict[m][n]=1;
                }
                else{
                    dict[m][n]=2;
                }
                return answer;
            }
            else {
                dict[m][n]=2;
                return false;
            }
        }
    }

    public boolean matches (String s, String p,int i, int j){
        if(i==0){
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}


