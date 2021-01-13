就是递归地操作，递归地参数就是两个指针（数组的左边界和右边界）

注意点：
1.先写基本结束条件，根据递归函数的意义来判断最简单的情况应该返回什么。
2.然后按照最正常的情况把主体框架写出来
3.考虑除了基本结束条件外的特殊情况，比如这题的特殊情况是游标卡出来的数组长度为3，
然后此时又分为例如前面都比后面小｛1，2，5｝ 、前面都比后面大｛7，8，5｝、前面一大一小｛7，2，5｝、前面一小一大｛2，7，5｝。
对着这4个实例在纸上过一遍看代码有没有bug

最后再写几个测试用例在IDEA里试试。先别提交。

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
