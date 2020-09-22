import java.util.*;

public class InsertSort {
    private static void sort(int [] list){

        for(int i=list.length-1;i>=0;i--){ //比如有十个，就先从前十个选出最大的放到最后面；然后从前九个选出最大的放到最后面。。。i是指最后一个元素的index
            int max = list[0]; //最大值
            int theIndexOfMax = 0; //最大值的index
            for(int j = 0;j<=i;j++){ //找出最大的
                if(list[j]>=max){
                    max=list[j];
                    theIndexOfMax = j;
                }
            }
            list[theIndexOfMax]=list[i];
            list[i]=max;
        }
    }

    public static void main(String[] args) {
        int list [] = {5,3,4,1,2};
        InsertSort.sort(list);
        System.out.println(Arrays.toString(list));
    }
}
