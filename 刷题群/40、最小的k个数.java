输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

方法1：堆排序法。维护一个长度是k的堆，要找最小的数字，那么就用大顶堆，每次判断新的数字是否比堆顶的大，大的话就不管，小的话就把堆顶的出队，然后小的入队。
堆在java中是优先队列实现，即PriorityQueue，java中默认是小顶堆，因此需要在该构造方法中加入一些参数，调整成大顶堆（这些参数的意义有时间再来看）。
空间复杂度就是O（k），时间复杂度的话，最好情况就是比较n次，然后之后的每次都大于堆顶的，就不用出入元素，就是O（n），最差是每次都要出堆入堆，出和入都是logK，
所以是O（nlogk）。
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        Queue<Integer>heap=new PriorityQueue<>(k, (i1, i2) -> Integer.compare(i2, i1));
        for(int x:arr){
            if(heap.size()<k)heap.offer(x);
            else{
                if(x<heap.peek()){
                    heap.poll();
                    heap.offer(x);
                }
            }
        }
        int[]res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=heap.poll();
        }
        return res;
    }
}
