每日温度https://leetcode-cn.com/leetbook/read/queue-stack/genw3/
请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。





方法1：两个for循环，暴力求解：
    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }



方法2：单调栈
单调栈的图示原理参考https://leetcode-cn.com/problems/daily-temperatures/solution/leetcode-tu-jie-739mei-ri-wen-du-by-misterbooo/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i=0;i<T.length;i++){
            if(stack.isEmpty() || T[stack.peek()] >= T[i] ){
                stack.push(i);
            }
            else if(T[stack.peek()] < T[i]){
                int x = stack.pop();
                result[x] = i-x;
                i--;
            }
        }
        return result;
    }
}


遇到的问题：如下是我写的代码，如果把第四行Stack类换一下，换成
Deque<Integer> stack = new LinkedList<Integer>(); （同时第6行的empty方法改成isEmpty方法）那么执行速度基本提高一倍。
如果再把Deque替换成LinkedList，那么速度又会提高大概5%。比较weird，有时间再来研究吧。



总结：涉及到要求找到大于或小于的关系的题，如果暴力法求解是用双循环的话（O(mn)），
可以通过单调栈（栈中元素是有大小顺序的，每次入栈先比较大小，符合则入栈，不符合则弹栈）的方式()减少复杂度为O(m)。










