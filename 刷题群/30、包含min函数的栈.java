定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.


我一开始是想用一个成员变量保存最小值，每次入栈都判断是否要更新最小值，然后调用min方法的时候直接输出最小值就行了
但是发现弹栈后，最小值可能会改变，因此最小值也应该跟着弹栈，那么就新弄一个栈b用来装最小值那个成员变量，弹栈压栈都与之前那个栈a同步。
注意弹栈后也要看是否更新保存最小值的那个成员变量。

class MinStack {
    Deque<Integer> a=new LinkedList<>();
    Deque<Integer> b=new LinkedList<>();
    int minNum;

    public MinStack() {

    }

    public void push(int x) {
        if(a.size()==0)minNum=x;
        else{
            if(x<minNum)minNum=x;
        }
        a.push(x);
        b.push(minNum);
    }

    public void pop() {
        a.pop();
        b.pop();
        if(b.size()!=0) minNum=b.peek();
    }

    public int top() {
        return a.peek();
    }

    public int min() {
        return b.peek();
    }
}
