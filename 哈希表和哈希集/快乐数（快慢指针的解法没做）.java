编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。

如果 n 是快乐数就返回 True ；不是，则返回 False 。


示例：

输入：19
输出：true
解释：
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1


方法1.我的方法思路：把输入的数字转换成字符串（这样才可以遍历），然后遍历得到每一个字符，求它们的平方（需要把字符先转换成字符串才能转换成Integer）。
然后判断平方和是否为1，如果不是则判断是否在set中，如果是则说明无限循环了，如果不是则把它记录在set中然后进行下一轮循环。 （用set的原因就是set的查找是0(1)）

需要改进的点：求各个位置的平方和我是转换了半天，很费劲，转换的底层原理我目前还没研究、可能代价较大。在方法2中会改进。


class Solution {
    public boolean isHappy(int n) {
        int sum;
        Set set = new HashSet();
        while(true){
            sum=0;
            String a = String.valueOf(n);
            for(int i=0;i<a.length();i++){
                sum += Math.pow(Integer.parseInt(String.valueOf(a.charAt(i))),2);
            }
            if(sum==1){
                return true;
            }
            if(set.contains(sum)){
                return false;
            }
            else{
                set.add(sum);
                n=sum;
            }
        }
    }
}


方法2：改进计算数字各个位的平方的方法，就不转换和遍历了，直接用利用（%10可以获得最后一位，/10可以去掉最后一位）来获得各个位的数值。
此时由于省去了每次循环时的类型转换、拆包装包，从而时间缩短了三分之二，打败100%用户（之前那种方法是打败7%用户。。。）。
class Solution {
    public boolean isHappy(int n) {
        int sum;
        Set set = new HashSet();
        while(true){
            sum=0;
            while (n>0){
                sum+= (n%10)*(n%10);
                n = n/10;
            }
            if(sum==1){
                return true;
            }
            if(set.contains(sum)){
                return false;
            }
            else{
                set.add(sum);
                n=sum;
            }
        }
    }
}

