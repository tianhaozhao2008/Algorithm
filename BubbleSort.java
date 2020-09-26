import java.util.Arrays;

public class BubbleSort {
    private static void sort(int [] list){
        for(int i=list.length-1;i>=0;i--){
            for(int j=0;j<i;j++){
                if(list[j]>list[j+1]){
                    int x = list[j];
                    list[j]=list[j+1];
                    list[j+1]=x;
                }
            }
        }
    }

    public static void main(String[] args) {
        int [] list={5,3,4,1,2};
        BubbleSort.sort(list);
        System.out.println(Arrays.toString(list));
    }
}
