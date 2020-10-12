根据 逆波兰表示法，求表达式的值。
有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

说明：
整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 
示例 ：

输入: ["2", "1", "+", "3", "*"]
输出: 9
解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9

逆波兰表达式：逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
逆波兰表达式主要有以下两个优点：
去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。

1.如下是我的代码，用if做的，通不过报错是内部java类有问题，比较weird，用第二种方法换成case，就过了。。。
class Solution {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String x:tokens){
            if(x=="+"){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a+b);
            }
            else if(x=="-"){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b-a);
            }
            else if(x=="*"){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(a*b);
            }            
            else if(x=="/"){
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b/a);
            }
            else{
                stack.push(Integer.valueOf(x));
            }
        }
        return stack.pop();
    }  
}


1.1 这个代码是我第一遍写的，写的比较乱，后来优化成了上面的那种，不需要弄什么哈希集合了。
class Solution {

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        Set<String> hashset = new HashSet<>(){{
            addAll(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","0"}));
        }};
        for(String x:tokens){
            if(hashset.contains(x)){
                stack.push(x);
            }
            else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int result;
                if(x=="+"){
                    result= a+b;
                }
                else if(x=="-"){
                    result=b-a;
                }
                else if(x=="*"){
                    result=a*b;
                }
                else{
                    result=b/a;
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        String[]a = {"2","1","+","3","*"};
        int b= evalRPN(a);
        System.out.println(b);
    }
}


2.这个是我把多条if改成case的版本：
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int a,b;
        for(String x:tokens){
            switch(x){
                case "+":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(a+b);
                    break;
                case "-":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b-a);
                    break;
                case "*":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(a*b);
                    break;
                case "/":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(b/a);
                    break;
                default:
                    stack.push(Integer.valueOf(x));
                    break;
            }
        }
        return stack.pop();
    }
}
