就是前部分是数值大小（因为不能超），后部分是全排列0000~9999

这题先在纸上画画图，把各种情况if 怎么处理简单列出来，先花个二十分钟列，列出来后就很快写出代码了。这题就是找下规律即可（我的答案与题解类似，就不看题解了。
之后还看到有用动态规划做的，不过感觉那个有点难想吧，直接找规律遍历即可）
输入一个整数n后，比如输入32425，那么实际上就是看0~32425这些数中的所有1的个数，那么我们固定五个位置，分别统计，第一个位置是1时有多少种情况，第二个位置是1时有多少种情况，
遍历一遍统计。保持一个原则就是固定一个位置的时候，其它位的变化，不能使数值整体大于输入的32425即可。
分三种情况：
1.当这一位是0时，如果它要变成1，就要前面的数变小1，比如前面是432，那么就只能是0~431这432种情况，不然就超了。然后后面的数字取任意，比如后面有3位，那么就可以取000~999，
一共10的三次方种。
2.当这一位是1时，比如前面是432，那么就是0~431这432种情况乘上后面的全排列10的三次方，再加上当前面是432时，后面的就不能超了，比如后面是132，那么后面就只能取0~132这133种情况
3.当这一位大于1时，比如前面是432，那么就是0~432这433种情况乘上后面的全排列10的三次方（此时不会越界）。

这题注意字符转int时，不能直接Inter.valueOf,而应该先把char用String.valueOf转换成String后再转成Int，如 Integer.valueOf(String.valueOf(num.charAt(i))

class Solution {
    public int countDigitOne(int n) {
        String num= String.valueOf(n);
        int sum=0;
        for(int i=0;i<num.length();i++){
            double pow = Math.pow(10, num.length() - i);
            if(Integer.valueOf(String.valueOf(num.charAt(i)))==0){
                sum+=n/((int) pow)*(int)Math.pow(10,num.length()-i-1);
            }
            else if(Integer.valueOf(String.valueOf(num.charAt(i)))==1){
                sum+=n/((int) pow)*(int)Math.pow(10,num.length()-i-1)
                        +n%(int)Math.pow(10,num.length()-i-1)+1;
            }
            else{
                sum+=(n/((int) pow)+1)*(int)Math.pow(10,num.length()-i-1);
            }
        }
        return sum;
    }
}
