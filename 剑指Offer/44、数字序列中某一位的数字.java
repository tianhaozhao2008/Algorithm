这题就是找规律，看评论说这种找规律的题在面试中出现的不多。
第一步：我们知道整个列表中是很多数字从0开始递增往上加，我们要知道输入的n是位于几位数，我们看到所有1位数占9*1个下标，所有2位数占9*10*2个下标，
所有3位数占9*100*3个下标。。我们就用n从1位数开始减，什么时候n减完变成负的了，我们就知道n是位于几位数了。
第二步：看n位于的这个几位数是多少。恢复到n最后一次减完变负数之前，此时就是从（比如n是三位数）100开始的，用n/3就知道具体是哪个数了。
第三步：看n位于的这个数的位数。用刚才的n%3就知道偏移了多少了。


注意题目中说n<2^31，int是四个字节即32位，正数的话最大是2^31次方-1，所以n自然是不会越界的，但是第一个while循环中n会减一个很大的数，从而变成负数，所以可能越界，
因此我们直接把n变成long类型。
注意类型转换分为：
1.数值的转换，前面直接加比如（int）即可。
2.变量类型的转换：不能直接加（long），只能新定义一个long的变量，然后把值赋过来（int赋值给long就会完成自动转换）。



class Solution {
    public int findNthDigit(int m) {
        long n=m;
        if(n==0)return 0;
        int digit = 1; //位数
        while(n>0){
            n=n-9*(long)Math.pow(10,digit-1)*digit;
            digit++;
        }
        digit--;
        n=n+9*(long)Math.pow(10,digit-1)*digit; //此时是从10^digit开始的第n个数，是digit位数。
        int which=(int)(n/digit); //表示到了第几个数了。
        int plus=(int)(n%digit); //表示这个数再往后偏移多少，如果是0就是刚才那个数的最后一位，否则是下个数的第plus位。
        if(plus==0){
            long num=(long)Math.pow(10,digit-1)+which-1;
            return (int)(num%10);
        }
        else{
            long num=(long)Math.pow(10,digit-1)+which;
            return (int)((num/((long)Math.pow(10,digit-plus)))%10);
        }
        //如果plus是0就取上面数的最后一位，否则就取10^digit+which+1的第plus位。
    }
}
