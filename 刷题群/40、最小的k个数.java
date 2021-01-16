输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。

方法1：堆排序法。维护一个长度是k的堆，要找最小的数字，那么就用大顶堆，每次判断新的数字是否比堆顶的大，大的话就不管，小的话就把堆顶的出队，然后小的入队。
堆在java中是优先队列实现，即PriorityQueue，java中默认是小顶堆，因此需要在该构造方法中加入一些参数，调整成大顶堆（这些参数的意义有时间再来看）。
空间复杂度就是O（k），时间复杂度的话，最好情况就是比较n次，然后之后的每次都大于堆顶的，就不用出入元素，就是O（n），最差是每次都要出堆入堆，出和入都是logK，
所以是O（nlogk）。 我自己想的方法也是用个k的长度的列表来保存最小的k个值，然后遍历，只不过我没有形成堆结构，因此我的复杂度是O（nk）。
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

方法2：快排的改进版。每次分成两部分，如果那一部分的长度大于k，那么就在那一部分中继续分；如果那一部分的长度小于k，那么就去另一部分把剩下的几个也找出来。如果那一部分等于
k，就不分了。
空间复杂度O（1），时间复杂度期望值是O（n）（即n+n/2+n/4+n/8...即2n），最坏情况是每次分只能分出来一个来，就是O（n*n）。
方法2的优势就是空间和时间复杂度，但方法二改动了原始数组，且当数据流过大时，比如内存都无法同时存放这么多数据时，方法2这种遍历数组的肯定就不行了。
快速排序直接背吧，边界条件太麻烦，没时间慢慢试了。。https://github.com/tianhaozhao2008/Algorithm/blob/master/QuickSort.java

这题邪门，最后一个测试用例就是过不了我擦，
class Solution {
    int[]arr;
    public int[] getLeastNumbers(int[] arr, int k) {
        if(k==0 ||arr.length==0)return new int[]{};
        this.arr=arr;
        partition(0,arr.length-1,k);
        int []res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=arr[i];
        }
        return res;
    }
    void partition(int left,int right,int k){
        if(left>=right)return;
        int tag=arr[(left+right)/2];
        int i=left;
        int j=right;
        while(i<=j) {
            while (arr[i] < tag) i++;
            while (arr[j] > tag) j--;
            if (i<=j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        if(j-left+1==k)return;
        else if(j-left+1>k) partition(left,j,k);
        else partition(i,right,k-j+left-1);
    }
}

