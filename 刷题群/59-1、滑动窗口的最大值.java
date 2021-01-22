方法1；就是每滑到一个窗口，都要算一遍这个窗口中的最大值。
一共n-k个窗口，每个窗口计算最大值需要遍历k次，所以时间复杂度是O（(n-k)*k），空间复杂度是O（1）。这种方法太无脑，懒得写了。

方法2：就是记录窗口最大值的位置，只要下个窗口不会划过那个最大值，就只需要比较一次即可；当然如果正好划过了那么还是要遍历k次。
这种方法不稳，最好的情况就是递增排列的，最大值永远不会划过，所以最好情况是O（n）；最差情况是递减排列的，每次最大值都会划过、用重新判断，
所以最差情况还是O（（n-k）*k）。

方法3：如果非要每次找最大值的时间复杂度都是O（1），那么只能牺牲空间，用k个空间保存从小到大的值。
即弄一个双向队列（之前第30题是用O（1）的复杂度获取栈中的最小值，那个是新弄一个辅助栈，这个是双向队列）。

1、每次向队列添加元素的时候，先看队列peek的元素是否已经划过了，划过了就出队
2.队列的peek存储最大值，要保持队列从peek到入口是递减的。因此如果要进入一个元素，就要先把这个队列中比这个元素小的都pop。

这题注意双端队列的api，如果是又要pop又要poll的，就不能push和offer混用的，如果你是push进去的，那么pop和poll都是弹栈；
如果你是offer进去的，那么pop和poll都是出队。因此，用双向队列专门的方法：
添加元素：addFirst 、addLast
查找元素：getFirst 、 getLast
删除元素：removeFirst、removeLast
class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();
        int []x=solution.maxSlidingWindow(new int[]{1,3,1,2},3);
        System.out.println(Arrays.toString(x));

    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0)return new int[]{};
        int [] res= new int[nums.length+1-k];
        Deque<Integer> deque=new LinkedList<>();
        for(int i=0;i<k-1;i++){
            while(deque.size()!=0 && deque.getLast()<nums[i]) deque.removeLast();
            deque.addLast(nums[i]);
        }
        for(int i=0;i<res.length;i++){
            while(deque.size()!=0 &&nums[i+k-1]>deque.getLast()) deque.removeLast();
            deque.addLast(nums[i+k-1]);
            res[i]=deque.peekFirst();
            if(deque.peekFirst()==nums[i]) deque.removeFirst();
        }
        return res;
    }
}
