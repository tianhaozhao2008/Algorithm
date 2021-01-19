要求逆序对，最直观想到的是两层for循环遍历，时间复杂度O（n*n），空间O（1），
一般O（n*n）的复杂度都可以优化成n*logn的，于是联想到归并排序的思想。

先复习一下归并排序，就是先对前后部分排序后，新建一个数组，然后比较前后排序好的部分的头部大小，一个个填进新数组，
然后再把新数组中的数赋值到原始数组中。


首先，对于一个数组，比如[7,5,6,4,6,2]，那么要求逆序对，相当于求位于数组前一半的对、位于数组后一半的对，再加上横跨前半和后半的逆序对。

因此创建一个求逆序对数量的函数，参数是数组的下标，你先传入0和5，表示计算0到5之间的逆序对，那么它先递归调用自身，计算前部分和后部分的逆序对，
然后再算横跨前后部分的。如何算横跨前后部分的呢？这一步前部分是7，5，6，后部分是4，6，2，如果遍历的话就友成了O（n*n）了，因此这里假设前部分和
后部分已经排好序了，即5，6，7，和2，4，6，这时对它们归并，新成立一个数组，先比较2更小，于是把2放进去，然后4更小，把4放进去，然后5更小，把5放进
去的同时，看右边已经放进去几个了，此时放进去了2个说明有2个比5小，所以逆序对+2，然后是两个6放进去（如果一样就左边先放，不然会出错），逆序对再+2，
然后7放进去，逆序对再+3，最后得到横跨前后部分的逆序对有7个。

归并排序写了点bug，查了十几分钟才搞定，看来对归并还是不熟，再记一遍归并排序的细节吧，达到快速排序那么熟就行了。
class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();
        int x=solution.reversePairs(new int[]{7,5,6,4});
        System.out.println(x);
    }

    public int reversePairs(int[] nums) {
        if(nums.length==0)return 0;
        return sort(nums,0,nums.length-1);
    }

    int sort(int[]num,int left,int right){
        if(left==right)return 0;
        int a=sort(num,left,(left+right)/2);
        int b=sort(num,(left+right)/2+1,right);
        int sum=0; //代表接下来横跨左右两部分的逆序对。
        //接下来是归并,从小到大排序
        int []temp=new int[right-left+1];
        int i=left; int j=(left+right)/2+1;
        for(int index=0;index<temp.length;index++){
            //下面两条if是比如左边全读完了，就剩下右边了，就直接填，就不比了，不然越界。
            if(i>(left+right)/2){
                temp[index]=num[j];
                j++;
                continue;
            }
            if(j>right){
                temp[index]=num[i];
                i++;
                sum+=j-((left+right)/2+1);
                continue;
            }
            //比较左右部分的起始数值的大小。
            if(num[i]<=num[j]){
                temp[index]=num[i];
                i++;
                sum+=j-((left+right)/2+1);
            }
            else{
                temp[index]=num[j];
                j++;
            }
        }
        for(i=0;i<temp.length;i++) {
            num[left]=temp[i];
            left++;
        }
        return a+b+sum;
    }
}

