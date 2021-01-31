剑指 Offer 05. 替换空格
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例 1：
输入：s = "We are happy."
输出："We%20are%20happy."

总结：java中字符串是final修饰的不可变常量，一旦确定就不能修改，这样设计的目的是为了安全性。
我们要修改的话，需要新建一个StringBuffer类型的变量，它可以用append方法添加，然后遍历字符串（这里用的是for in，先把字符串用toCharArray转换成可迭代的）
最后return的时候再把StringBuffer类型转换回String类型。

class Solution {
    public String replaceSpace(String s) {
        StringBuffer buffer = new StringBuffer();
        for(char c:s.toCharArray()){
            if(c==' '){
                buffer.append("%20");
            }
            else{
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
}
