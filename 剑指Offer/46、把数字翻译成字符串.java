就是简单的动态规划，状态转移函数注意下，我一开始写错了写成那两位数<=25就算，但实际上还要>=10才可以，因为08、09这种是变不了的。

比较那两位数与10和25的大小关系：
1.一种是通过charAt提取出来然后通过String.valueOf转换成字符串再通过Integer.valueOf转换成int（之所以这么麻烦是因为char不能直接转int）
2.另一种是通过String.subString截取出来长度为2的字符串，然后通过compareTo方法来与"25"和"10"做减法（就是按照编码来减的），看是否大于0. 
（如果是char的话可以直接减，就不用comapreTo了）

class Solution {
    public int translateNum(int num) {
        String nums=String.valueOf(num);
        int a=1;int b=1; int c=1;
        for(int i=1;i<nums.length();i++){
            if(nums.substring(i-1,i+1).compareTo("25")<=0 &&nums.substring(i-1,i+1).compareTo("10")>=0){
                c=a+b;
            }
            else c=b;
            a=b;
            b=c;
        }
        return c;
    }
}

下面这种方法是改进了一点，就是不新建字符串了，节省了空间，直接在原始num上改，然后倒着做，num%10是获得最后一位，num/10是删除最后一位。
动态规划的dp[n]表示倒数第1个到倒数第n个这么多位数的翻译方法，然后就变成了倒过来了。
class Solution {
    public int translateNum(int num) {
        int a=1;int b=1;
        int c=1;
        int xLast=num%10;
        num=num/10;
        int xNow=num%10;
        while(num!=0){
            if(xNow*10+xLast>=10&&xNow*10+xLast<=25)c=a+b;
            else c=b;
            a=b;
            b=c;
            num=num/10;
            xLast=xNow;
            xNow=num%10;
        }
        return c;
    }
}
