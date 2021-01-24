这题感觉就是面向测试用例编程的。。题目里又没说数字不能以0开头，然后还要去掉开头的0。。
两个注意的点：
1.字符转数字，不用傻乎乎弄哈希了，直接让字符-'0'，就是让字符的ASCII码与'0'的ASCII码做减法，结果就是int数字。
2.判断是否越界，正数的话，超了2147483647就都返回2147483647，负数的话，超了2147483648就都返回-2147483648。
由于int最大就只能表示这么大，所以不能直接跟那个越界的数比，所以关键点就是：去掉最后一位，用前面的比。
因此越界的情况是：
（1）数字长度超过10位了。
（2）数字长度等于10位，此时获得前9位，如果前9位>214748364,则越界；如果前9位=214748364但第10位>7，则越界。
    因此配套的把字符串转换成数字时，不能从低位开始乘10的次方了，而是应该从高位开始算，才能得到前9位数。
     如果从高位算，那么首先让res=最高位的值，然后每次迭代都让res=res*10+下一位。

class Solution {
    public int strToInt(String str) {
        if(str.length()==0) return 0;
        StringBuilder builder=new StringBuilder();
        boolean ifFirst=true;
        int tag=1;
        for(int i=0;i<str.length();i++){
            char letter=str.charAt(i);
            if(letter==' '){
                if(ifFirst==true) continue;
                else break;
            }
            if(letter!='+'&&letter!='-'&&(letter<'0'||letter>'9')&&ifFirst) return 0;
            if(letter=='+') {
                if(ifFirst==true)tag=1;
                else break;
                ifFirst=false;
                continue;
            }
            if(letter=='-'){
                if(ifFirst==true)tag=-1;
                else break;
                ifFirst=false;
                continue;
            }
            ifFirst=false;
            if(letter>='0' &&letter<='9') {
                if(letter=='0'&&builder.length()==0)continue;
                builder.append(letter);
            }
            else break;
        }
        if(builder.length()==0) return 0;
        
        //截止到这一步，StringBuilder里的字符就都是数字字符了。接下来先判断是否越界
        boolean passOver=false;
        if(builder.length()>10)passOver=true;

        int res=(builder.charAt(0)-'0');
        for(int i=0;i<builder.length()-1;i++){
            res=res*10+(builder.charAt(i+1)-'0');
            if(i==7){
                if(builder.length()==10){
                    if(res>214748364)passOver=true;
                    if(res==214748364 &&builder.charAt(9)>'7') passOver=true;
                }
            }
        }
        if(passOver){
            if(tag==1)return 2147483647;
            else return -2147483648;
        }
        return tag*res;
    }
}
