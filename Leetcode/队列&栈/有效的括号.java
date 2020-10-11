有效的括号
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true


class Solution {
    public boolean isValid(String s) {
        if(s.length()%2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<Character,Character>(){{
            put('(',')');
            put('[',']');
            put('{','}');
        }};
        for(int i=0;i<s.length();i++){
            char x = s.charAt(i);
            if(map.containsKey(x)){
                stack.push(x);
            }
            else{
                if(stack.isEmpty() || map.get(stack.pop()) != x){
                    return false;
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}

总结：一般用到一一对应的关系，比如左括号和右括号的匹配，最好使用哈希表，
不然判断对应的时候要用多个if判断，代码看起来很乱。
