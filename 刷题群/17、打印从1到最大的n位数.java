剑指 Offer 17. 打印从1到最大的n位数 https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/

输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

示例 1:
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]

解析：这题很简单，返回的是数组，直接弄即可。
class Solution {
    public int[] printNumbers(int n) {
        int max= (int) (Math.pow(10,n)-1);
        int []res=new int[max];
        for(int i=0;i<res.length;i++){
            res[i]=i+1;
        }
        return res;
    }
}
但看题解说并不简单，面试的话需要考虑大数越界，要你把大数转换成字符串：但是字符串是无法比较大小和按照数值顺序排的，
所以采用一种分治的算法来生成，类似树的遍历那样：

关键点：（一句话总结的话就是用数组表示这个n位数，然后对这个数组进行dfs递归）

1.结果由字符串表示，那么就不能是字符串数组（否则数组下标也会越界），所以用一个大的字符串表示（里面不同数用，隔开）。
采用可变字符串类型StringBuilder作为最后的那个结果
    该类型的方法们要熟悉，如append方法是在尾部添加元素、toString方法是将可变数组转换成字符串、deleteCharAt方法是删除指定下标的字符
    注意区分StringBuilder类的toString方法，与String.valueOf这个静态方法（这个方法是其它不同类型转换的，比如把字符数组转换合并成字符串）
    
2.想到用递归dfs来做，最高位for循环设成0~9，循环体内对下一位调用自身。。直到到达个位设置好后，把这个数加入StringBuilder。
3.配合dfs递归，就用一个n位数的字符数组来做，对数组从0号的高位开始做递归。结束后把数组通过String.valueOf方法转换成字符串后加入StringBuilder后面
        （注意去掉那些0开头的，所以可先对数组循环找到非0开头的下标，然后用Arrays.copyOfRange方法截取成一个新的数组）
        
class Solution {
    char[]loop={'0','1','2','3','4','5','6','7','8','9'};
    char[]num;
    StringBuilder res = new StringBuilder();

    public static void main(String[] args) {
        Solution solution=new Solution();
        String output=solution.printNumbers(3);
        System.out.println(output);
    }
    public String printNumbers(int n) {
        num=new char[n];
        dfs(0,n);
        res.deleteCharAt(res.length()-1); //删除最后的逗号
        res.deleteCharAt(0); //删除最开始的0
        res.deleteCharAt(0); //删除最开始0后面的那个逗号
        return res.toString();
    }
    void dfs(int begin,int n){
        if(begin==n){ //begin到n的时候说明已经到个位了，此时应该添加到可变字符串中
            //下面是遍历找到非0开头的位置，然后等会用copyOfRange方法去掉前面那些0
            int i=0;
            while(num[i]=='0' && i< num.length-1){
                i++;
                //System.out.println(i);
            }

            res.append( String.valueOf(Arrays.copyOfRange(num,i,num.length)) + ",");
            return;
        }
        for(char x:loop){
            num[begin]=x;
            dfs(begin+1,n);
        }
    }
}
