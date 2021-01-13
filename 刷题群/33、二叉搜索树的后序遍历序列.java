就是递归地操作，递归地参数就是两个指针（数组的左边界和右边界）

注意点：
1.先写基本结束条件，根据递归函数的意义来判断最简单的情况应该返回什么。
2.然后按照最正常的情况把主体框架写出来
3.考虑除了基本结束条件外的特殊情况，比如这题的特殊情况是游标卡出来的数组长度为3，
然后此时又分为例如前面都比后面小｛1，2，5｝ 、前面都比后面大｛7，8，5｝、前面一大一小｛7，2，5｝、前面一小一大｛2，7，5｝。
对着这4个实例在纸上过一遍看代码有没有bug

最后再写几个测试用例在IDEA里试试。先别提交。


时间复杂度想了很久，我真是颓了。
1.首先就是比较好的情况，每次都能对半分的话，那么就类似归并排序那样，一共分logN次就行了，每次都是遍历一遍（其实是一开始遍历N个，然后每次
提出来节点后N就变小，平均下来是N/2）。那么相乘就认为是O(NlogN)的复杂度。
2.然后是不好的情况，每次都无法对半分，只能分出来一个（类似快速排序的最差情况），那么就要分N次，每次遍历一遍（平均N/2），那么复杂度就是O(N^2)的。

空间复杂度：这里算法并没有开辟新的结构如链表数组等，这里的空间复杂度考虑的就是递归栈的深度。
递归时会把后面的代码压栈，一边压栈一边弹栈的情况就很浅，但如果一直压栈那么递归栈就很深，这题最坏情况是每次只能分割成1个和n-1个，那么递归栈最坏情况深度就是n，
所以空间复杂度是O（n）。

关于递归栈的深度，参考下面关于快速排序的题：
1.对n个记录的线性表进行快速排序为减少算法的递归深度,以下叙述正确的是()
正确答案: A   
A 每次分区后,先处理较短的部分
B 每次分区后,先处理较长的部分
C 与算法每次分区后的处理顺序无关
D 以上三者都不对

2.采用递归方式对顺序表进行快速排序，下列关于递归次数的叙述中，正确的是()
正确答案: D   
A 递归次数与初始数据的排列次序无关
B 每次划分后，先处理较长的分区可以减少递归次数
C 每次划分后，先处理较短的分区可以减少递归次数
D 递归次数与每次划分后得到的分区处理顺序无关

class Solution {
    int[]postorder;

    public static void main(String[] args) {
        Solution a=new Solution();
        System.out.println(a.verifyPostorder(new int[]{1,2,3}));
    }
    public boolean verifyPostorder(int[] postorder) {
        this.postorder=postorder;
        if(postorder.length==0)return true;
        return judgePostOrder(0,postorder.length-1);
    }
    boolean judgePostOrder(int start,int end){
        if(end-start<=1)return true;
        int headVal=postorder[end];
        int aStart=start; //定义左右子树的边界下标
        int bEnd=end-1;
        //下面这两个是在循环中确定的，但是循环中可能不满足条件而没有初始化，所以这里先默认初始化成无法进入循环if语句的值
        int aEnd=bEnd;
        int bStart=end;
        for(int i=aStart;i<=bEnd;i++){
            if(postorder[i]>headVal){
                bStart=i;
                aEnd=i-1;
                break;
            }
        }
        for(int i=bStart;i<=bEnd;i++){
            if(postorder[i]<headVal)return false;
        }
        return judgePostOrder(aStart,aEnd)&&judgePostOrder(bStart,bEnd);
    }
}
