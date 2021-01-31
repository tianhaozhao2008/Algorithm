剑指 Offer 14- II. 剪绳子 II   https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

示例 1：
输入: 2
输出: 1
解释: 2 = 1 + 1, 1 × 1 = 1

示例 2:
输入: 10
输出: 36
解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 
提示：
2 <= n <= 1000


总结：这道题跟之前第一个版本的区别就是，n可以1000这个大，那么拆分成乘积之后（比如3的300多次方）就特别大，会越界。
所以题目的要求是对1e9+7取模，即使结果求对1e9+7的余数，就可以缩小这个结果。
参考题解：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/solution/javatan-xin-si-lu-jiang-jie-by-henrylee4/

方法1：如果动态规划，则需要用BigInteger类的大数运算（否则，递推函数中比较max时，要直接比，不能取余，但直接比又会越界）。否则只能用贪心（数学推导）？）。
但听说BigInteger这个类又很慢，而且动态规划是穷举所有情况再比较，贪心是就一种情况直接算，所以时间复杂度差了行情了。

我一开始直接把状态转移方程写成了sum=Math.max(sum, x*Math.max((i-x),dp[i-x])%1000000007);这样的话是取了余数了，但是涉及到比较，你max函数里的dp[i-x]已经取过余了，
因此这里比较就会出错。我擦。
再明确一下取余规则：无论加减乘法，括号内随便取余不取余都行，括号外记得取余就好。比如（a*b*c）%3 ==（a%3 * b%3 * c%3）%3 == (a* b%3 *c)%3 ==[(a*b)%3 * c] %3

方法2：采取贪心算法、推导：思考拆分的最终可以由哪些部分组成：

1.如果拆成1的话，当总数大于3时肯定不行的，因为拆成1相当于总数减1但乘积不变；
2.拆成2或3是可以的，拆成4的话就相当于是两个2，因此4的时候也就不考虑了，直接当成2*2；
3.如果拆成5、6、7、8...的话，肯定不行的，比如5还能继续细分成2*3=6，可以变得更大；
4.具体是拆2还是拆3呢？假设现在是n，那么比较3*(n-3)和2*(n-2)的关系，发现当n大于5时，优先拆3，这就是一种局部最优解，那么我们一直采用局部最优解，即贪心算法。
  就是尽可能拆成多个3相乘，那么就是类似3的几百次方，然后再取余。

那么就用到取模运算的法则：(a * b) % p = (a % p * b % p) % p ，（这个取模在括号内随便拆，就如第32行写的那样）
即可以每乘一次3都取一次模，最终的结果再取一次模，这样就不会越界了。
（注意涉及到越界问题的，我们都换成long类型，即64位二进制。如果long与int类型做运算或比较，int类型就会自动填充成long的长度。可以通过(int)来把long变量转换成int)
  class Solution {
    public int cuttingRope(int n) {
        if(n==2)return 1; //n=2和3时是特例。单独考虑
        if(n==3) return 2;
        int pow=n/3;   //次方
        int rem=n%3;   //余数
        int k=1;
        if(rem==1){
            pow--;
            k=4;
        }
        else if(rem==2){
            k=2;
        }
        //  int result=3^pow *k;
        long result=1;
        for(int i=1;i<=pow;i++){
            result =(result*3)%1000000007;
        }
        result=(result*k)%1000000007;
        return (int)result;
    }
}

方法2的优化：如果是300次方，那么复杂度就是300.这里采用快速幂的方式来优化，比如3的300次方，每次都把指数缩小一半，然后让底数乘2.（相当于复杂度缩小一半再加1）
注意快速幂的同时，结合上面的取模运算规律（从而使不越界的同时减低复杂度）。
备注：取模运算规则和快速幂，参考https://blog.csdn.net/qq_19782019/article/details/85621386 以及题解。
class Solution {
    public int cuttingRope(int n) {
        if(n==2)return 1; //n=2和3时是特例。单独考虑
        if(n==3) return 2;
        int pow=n/3;   //次方
        int rem=n%3;   //余数
        long k=1;
        if(rem==1){
            pow--;
            k=4;
        }
        else if(rem==2){
            k=2;
        }
        long base=3;
        //  int result=base^pow *k;
        //long result=1;
        if(pow==0){  //特殊临界情况
            return (int)k;
        }
        while(pow>=2){
            if(pow%2==1){
                pow--;
                k=k*base%1000000007;
            }
            else{
                pow=pow/2;
                base=base*base%1000000007; //这里不能写成^2,否则是异或操作。
            }
        }
        return (int)(base*k%1000000007);
    }
}
