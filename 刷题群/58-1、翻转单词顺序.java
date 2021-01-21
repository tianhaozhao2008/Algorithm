方法1：直接用split，时间空间复杂度都是O（n）。
注意点：判断字符串是否相等我又用的==。。。浪费了我20分钟debug擦。
class Solution {
    public String reverseWords(String s) {
        String x[]=s.split(" ");
        StringBuilder w=new StringBuilder();
        for(int i=x.length-1;i>=0;i--){
            if (!x[i].equals(" ") &&!x[i].equals("")){
                w.append(x[i]+" ");
            }
        }
        //下面判断不等于0是防止输入的都是空格，然后w里面啥也没装，等会下标就会越界
        if(w.length()!=0) w.deleteCharAt(w.length()-1);
        String result=w.toString();
        return result;
    }
}
