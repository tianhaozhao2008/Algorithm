剑指 Offer 16. 数值的整数次方  https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。

示例 1:
输入: 2.00000, 10
输出: 1024.00000

示例 3:
输入: 2.00000, -2
输出: 0.25000
解释: 2-2 = 1/22 = 1/4 = 0.25
 
说明:
-100.0 < x < 100.0
n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 

总结：就是快速幂的方式，减少算法复杂度。（看题解中说除2取余等使用位运算加速，我就觉得这种不应该是编译器去做的优化吗？
所以我没有做位运算加速，也打败了96%，所以事实是就不需要位运算加速）

这题不讲武德，说不需要考虑大数越界，结果他妈给我个-2147483648（-2^32），（int类型的范围是[-2^32, 2^32-1]，然后我把n变成-n时就越界。
最后还是强行把参数的类型换成long）
说明：如果用long x=2147483648；就会提示int越界，因为书写的数字默认是int类型，然后把这个int类型赋值给long类型，你的int类型就直接越界了
所以要么拆开成long x=2147483647+1  或者直接long x=2147483648L， 结尾的L表示这个当成long类型。
class Solution {
    public double myPow(double x, long n) {
        if(n==0){
            return 1;
        }
        if(n<0){
            x=1/x;
            n=-n;
        }
        double base=x;
        long exponent=n;
        double k=1;
        while(n>=2){
            if(n%2==0){
                n=n/2;
                base=base*base;
            }
            else{
                n--;
                k=k*base;
            }
        }
        return base*k;
    }
}
