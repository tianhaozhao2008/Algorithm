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

总结：就是快速幂的方式，减少算法复杂度。然后除2取余等使用位运算加速。
具体实现细节有很多，写代码的时候再参考那些。
