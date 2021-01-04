剑指 Offer 10- I. 斐波那契数列  https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
（答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。）


总结：取模就是java怕大数越界（java中int是32位二进制，如果是python的话就不用考虑越界）。比如对a取模的话，本质就是把大数缩小到0~a-1的范围内。
（a+b）%c = （a%c + b%c）%c ，根据这条，f(n-1)和f(n-2)都是取模之后的，那么取模之前的f(n-1)和f(n-2)相加取模，就等于取模之后的f(n-1)和f(n-2)相加取模。

带备忘录的递归：
class Solution {
    int[]dict;
    public int fib(int n) {
        dict=new int[n+1];
        Arrays.fill(dict, -1);
        return getResult(n);
    }
    int getResult(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(dict[n]!=-1){
            return dict[n];
        }
        int answer=(getResult(n-1)+getResult(n-2))%1000000007; //getResult(n-1)和getResult(n-2)是已经取过模的，所以这里直接相加再取模即可。
        dict[n]=answer;
        return answer;
    }
}
