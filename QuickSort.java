/*
快速排序：就是先随便找一个数（比如数组中间的一个数值作为tag，然后使数组左边都比这个数小，右边都比这个数大），然后再递归地对左边和右边再次排序（直到要排序的数组长度小于等于2时，设置基本结束条件）
快排和归并的一个区别就是快排不需要申请额外空间，那么是如何排序使左边小右边大的呢？想了很久，后来看答案是用两个下标，起始位置是i，终止位置是j，然后如果符合大小关系就逐步i++，j--，向中间缩小，直到不符合关系了，
就交换i和j的元素，然后继续向中间缩小，直到i不小于j。
搞完后递归调用自身，分别对左边和右边排序，刚才两个游标最终是j在左i在右，所以调用自身时j和i是两块的中间分界线。
注意基本结束条件很关键，这里很容易出错，建议这种情况动笔画个图，到最简单的数组长度为2，为3时，看看需要如何处理、是否会出错，总结出基本结束条件。
*/



import java.util.*;

public class QuickSort {
    private static void sort(int []list,int start,int end){
        int tag = list[(start+end)/2];
        int i =start;
        int j =end;
        //下面这个基础结束条件很关键
        if(j-i<=1){
            if(list[i]>list[j]){
                int x =list[i];
                list[i]=list[j];
                list[j]=x;
            }
            return;
        }
        while(i<j){
            while(list[i]<tag){
                i++;
            }
            while(list[j]>tag){
                j--;
            }
            if(i<j){
                int x =list[i];
                list[i]=list[j];
                list[j]=x;
                i++;
                j--;
            }
        }
        QuickSort.sort(list,start,j);
        QuickSort.sort(list,i,end);

    }

    public static void main(String[] args) {
        int []list={5,3,4,1,2};
        QuickSort.sort(list,0,list.length-1);
        System.out.println(Arrays.toString(list));
    }
}
