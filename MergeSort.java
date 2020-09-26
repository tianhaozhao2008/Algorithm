/*
首先就是这种归并排序或快速排序涉及到对不同的部分进行排序，所以构造函数肯定要传入待排序的起止下标。
这个归并排序本身递归并不难，难点是当左边和右边排好序后如何merge，merge函数怎么写：
merge函数：（经验是这种对条件判断复杂一些的，用while循环更简便；如果单纯迭代的话用for循环）
1.首先设置i和j分别作为左边和右边数组的起始坐标，然后比较i和j位置的元素大小，小的就放入申请的空间然后对应的坐标（i或j ++）
2.将没放完的那部分，剩下的按照顺序放入申请的空间（可能是左边没放完，要不就是右边没放完）
3.把申请的空间中排好序的内容填回去。
*/
import java.util.Arrays;

public class MergeSort {
    private static void sort(int []list,int start,int end){
        int middle=(start+end)/2;
        if(middle>start){
            MergeSort.sort(list,start,middle);
            MergeSort.sort(list,middle+1,end);
        }
        MergeSort.merge(list,start,middle,end);
    }
//将两个已经排序的部分，合并在一起。参数一个是数组，另外三个是起始、中间、结束的下标
    private static void merge(int []list,int start,int middle,int end){
        int [] newSpace = new int [end-start+1];
        int i = start;
        int j = middle+1;
        int m = 0; //m是新申请的数组的下标
        //首先排序，比较之后把小的放入申请的数组中。
        while(i<=middle && j<=end){
            if(list[i]<list[j]){
                newSpace[m]=list[i];
                m++;
                i++;
            }
            else{
                newSpace[m]=list[j];
                m++;
                j++;
            }
        }
        //如果前部分的有剩余，则把它剩余的填进去
        while(i<=middle){
            newSpace[m]=list[i];
            m++;
            i++;
        }
        //如果后部分的有剩余，则把它剩余的填进去
        while(j<=end){
            newSpace[m]=list[j];
            m++;
            j++;
        }
        //把新申请的这个数组中的内容，填回去到原始的那个list.
        m=0; //重新归零新申请数组的下标。
        i = start; //重新调回数组下标的起始位置。
        while(m<newSpace.length){
            list[i] = newSpace[m];
            i++;
            m++;
        }
    }

    public static void main(String[] args) {
        int [] list={5,3,4,1,2};
        MergeSort.sort(list,0,list.length-1);

        System.out.println(Arrays.toString(list));
    }
}
