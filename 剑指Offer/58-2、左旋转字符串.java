方法1：面试不要用、直接用substring，时间O（n），因为该函数是一个一个char弄过来的（具体细节不了解）。空间也是O（n）。
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n,s.length())+s.substring(0,n);
    }
}

方法2：遍历：用StringBuilder类型的可变字符串，然后一个一个字符加上去。时间空间都是O（n）。
这种最后要把StringBuilder转换成字符串，就需要一点时间或许。因此时间比上一种慢很多，上一种0ms，这种4ms。
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder res=new StringBuilder();
        for(int i=n;i<s.length();i++){
            res.append(s.charAt(i));
        }
        for(int i=0;i<n;i++){
            res.append(s.charAt(i));
        }
        return res.toString();
    }
}

方法3：遍历：不用StringBuilder，而是每次新创建一个字符串，然后新拼接起来前两个。
这种时间空间也是O（n），但这种要创建n次新的字符串、申请内存等，所以慢。
这种更慢，这个执行要70ms，上个执行要4ms，上上个执行要0ms。但是三个占的空间都基本相同。
class Solution {
    public String reverseLeftWords(String s, int n) {
        String res="";
        for(int i=n;i<s.length();i++){
            res=res+s.charAt(i);
        }
        for(int i=0;i<n;i++){
            res=res+s.charAt(i);
        }
        return res;
    }
}
