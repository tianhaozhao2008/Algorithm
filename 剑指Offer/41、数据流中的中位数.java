设计一个支持以下两种操作的数据结构：
void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。

中位数就是先排序，然后取中间（如果长度是偶数的话就取中间两个平均，int转double的话除2.0就行）。

我首先想到的是考虑链表还是ArrayList，涉及取中间和插入元素排序两个操作
1.取中间的话，ArrayList就是O（1），链表就比较麻烦要遍历一遍到中间，除非每次添加元素时都维持一个指针指向中间节点，才能O（1）
2.插入元素的话，假设之前已经排序好了，ArrayList就是先二分找到位置后，整体再平移，是O（n），链表是遍历找到位置后直接改指针，也是O（1）。因此似乎ArrayList简单点。

题解中krahets大佬讲的是用一个小顶堆一个大顶堆，让这两个堆的size相差不超过1，
那么取元素时：1.大顶堆size大就取大顶堆的peek  2.小顶堆的size大就取小顶堆的peek  3.两个size一般大就取两个的peek平均值
添加元素时：
1.两个堆一样大，就看节点跟peek相比，决定插入哪一个。
2.装小数的大顶堆的size大，就看插入的num如果比装小数的大顶堆的peek大，就直接装大数的插入小顶堆；否则就先把小顶堆poll出来一个值offer到大顶堆中，然后再把num插入到小顶堆。
3.装大数的小顶堆的size大，与上面相反。

这道题注意大小顶堆别搞反了就行、特殊处理size为0的初始情况，然后注意Java默认小顶堆，大顶堆的话就在构造方法的括号内加上参数(a,b)->(b-a)

class MedianFinder {
        Queue<Integer>heap1;
        Queue<Integer>heap2;
    /** initialize your data structure here. */
    public MedianFinder() {
        heap2=new PriorityQueue<>();
        heap1=new PriorityQueue<>((a,b)->(b-a));
    }

    public void addNum(int num) {
        if(heap1.size()==heap2.size()){
            if(heap1.size()==0 && heap2.size()==0)heap1.offer(num);
            else if(num<heap1.peek()) heap1.offer(num);
            else heap2.offer(num);
        }
        else if(heap1.size()>heap2.size()){
            if(heap2.size()==0){
                if(num>heap1.peek())heap2.offer(num);
                else{
                    heap2.offer(heap1.poll());
                    heap1.offer(num);
                }
            }
            else{
                if(num>heap1.peek()) heap2.offer(num);
                else{
                    heap2.offer(heap1.poll());
                    heap1.offer(num);
                }
            }
        }
        else{
            if(heap1.size()==0){
                if(num<heap2.peek()) heap1.offer(num);
                else{
                    heap1.offer(heap2.poll());
                    heap2.offer(num);
                }
            }
            else{
                if(num<heap2.peek()) heap1.offer(num);
                else{
                    heap1.offer(heap2.poll());
                    heap2.offer(num);
                }
            }
        }
    }

    public double findMedian() {
        if(heap1.size()==heap2.size()) return (heap1.peek()+heap2.peek())/2.0;
        else if(heap1.size()>heap2.size()) return heap1.peek();
        else return heap2.peek();
    }
}
