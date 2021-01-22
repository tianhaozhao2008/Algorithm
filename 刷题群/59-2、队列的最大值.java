这题就是使获取队列最大值的方法的复杂度是1。类似之前30题是使获取栈的最大值的复杂度是1。
这题跟刚才那题本质上一样，刚才的滑动窗口，本质上就是每滑动一次，相当于出队一次+入队一次。

因此，这题建立一个辅助的双向队列：
入队时：把元素添加到辅助容器上面之前，先把容器上面所有小于此元素的都删了；
出队时：如果出队的元素值等于辅助容器最下面的最大值时，让辅助容器最下面的出队。

注意点：（比较时要用equals而不是==的几种情况，强化记忆一下）
这题写了个bug花了我快一个小时找。即对于List、Deque这些类型的pop、peek的返回值是一个对象而不是值，当它和常量比较时可以用==因为
会自动转换；当两个peek的返回值比较时，其实比较的是对象地址，因此不能用==而应该用.equals()比较。但是当这个数小于128时，用==判断对象的地址
是一样的，原因如下：JVM会自动维护八种基本类型的常量池，int常量池中初始化-128~127的范围，所以当为Integer i=127时，在自动装箱过程中是取自常
量池中的数值，而当Integer i=128时，128不在常量池范围内，所以在自动装箱过程中需new 128，所以地址不一样。

class MaxQueue {
    Queue<Integer>queue;
    Deque<Integer> deque;
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if(deque.size()==0)return -1;
        else return deque.getFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while(deque.size()!=0&&value>deque.getLast()) deque.removeLast();
        deque.addLast(value);
    }

    public int pop_front() {
        if(queue.size()==0) return -1;
        if(queue.peek().equals(deque.getFirst())) {
            deque.removeFirst();
        }
        return queue.poll();
    }
}
