剑指 Offer 20. 表示数值的字符串

请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。

总结：这题用有限状态机识别。关于识别的规则很混乱，评论区很多人吐槽面向测试用例编程，因此状态转移的HashMap我直接copy，后面的再自己写的。
关键点：
1.设置一个Hash数组，数组不同下标表示不同当前状态对应的Hash，每个Hash保存输入的字符与对应要转移的状态。（注意一下Hash数组初始化的方式，有点乱，以后学全JavaSE再来看）
2.设置初始状态是0，然后for循环字符串不断查表改变状态即可。

class Solution {
    public boolean isNumber(String s) {
        Map[] transStates = {
                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1.
                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap<>() {{ put('d', 3); }},                                        // 4.
                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap<>() {{ put('d', 7); }},                                        // 6.
                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap<>() {{ put(' ', 8); }}                                         // 8.
        };
        int state=0;
        for(char x:s.toCharArray()){
            if(x>='0' &&x<='9'){x='d';}
            else if(x=='+'|| x=='-'){x='s';}
            else if(x=='e'||x=='E'){x='e';}
            if(!transStates[state].containsKey(x)){return false;} //如果不包含某个状态就直接返回false
            state= (int) transStates[state].get(x);
        }
        if(state==2||state==3||state==7||state==8){
            return true;
        }
        else{
            return false;
        }

    }
}
