方法1：我首先想到的就是位运算，但是我想的是从低位遍历到高位（实现遍历就要比如与1做与或亦或等操作然后左移右移等），
然后把这一位的两个数以及进上来的这三个数做亦或操作，结果就是这个位的数值，但是要再分情况判断是否有进位。因此过于麻烦。

方法2：看题解的，很清晰。就是把a+b的结果，转换成a+b的结果中不算进位（就是各个位亦或），然后加上进位（就是各个位作与操作，结果再左移），
一直转换，直到进上来的位是0时，就结束。
时间复杂度：每次进位都要左移，因此最差情况也会每次增加一位是0，那么一共32位就最多32次也能结束。所以是O（1）。
（当时写这个代码的时候总想用递归，感觉直接写迭代有点乱，原因是没弄清变化关系，
因此写的时候明确：每次把a^b给a，把(a&b)<<1给b，直到b==0就结束返回a）

class Solution {
    public int add(int a, int b) {
        int a1;
        while(b!=0){
            a1=a^b;
            b=(a&b)<<1;
            a=a1;
        }
        return a;
    }
}

下面这个是递归的版本： 更加清晰明了了。
class Solution {
    public int add(int a, int b) {
        if(b==0)return a;
        return add(a^b,(a&b)<<1);
    }
}

题解中作者写道：
Q ： 若数字 a 和 b 中有负数，则变成了减法，如何处理？
A ： 在计算机系统中，数值一律用补码来表示和存储。因此加减法可以统一处理（CPU只有加法器）。所以正负数其实是不用区分的。


