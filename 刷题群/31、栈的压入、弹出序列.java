输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

示例 1：
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1


方法1：数学推导、通过某些公式关系，直接由时间复杂度O（1）或许就能得到结果，但是没有看到有人类似的做法。下面这种加一个辅助栈的方法是我想的、与官方题解和评论区的方式一样。
方法2：弄一个栈，然后两个指针分别指向两个数组，模拟操作，看每一步是否相符。比较简单。
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length!=popped.length)return false;
        if(pushed.length==0)return true;
        Deque<Integer> stack=new LinkedList<>();
        int j=0; //j是指向popped数组起始位置
        for(int i=0;i<pushed.length;i++){  //i指向pushed数组的位置
           stack.push(pushed[i]);
           while(j<popped.length&&stack.size()!=0&&popped[j]==stack.peek()){
               stack.pop();
               j++;
           }
        }
        if(stack.size()!=0){
            for(int k=j;k<popped.length;k++){
                if(stack.pop()!=popped[k])return false;
            }
        }
        return true;
    }
}
