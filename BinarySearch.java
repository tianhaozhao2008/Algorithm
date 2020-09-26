/*
二分查找我是用递归实现的。当然不递归也可以，构造函数就不用传入下标了，具体就不说了。
我想说的就是对于递归函数，如果有返回值的话，那么调用自身的时候一定前面加上return，不然返回不过来（否则调用的子函数的返回值，不会反回到当前函数的返回值，因此你会看不到子函数的返回值）。
 */

public class BinarySearch {
    private static int search(int[]list,int key,int start,int end){
        int mid = (start+end)/2;
        if(start==end && list[start]!=key){  //基本结束条件
            System.out.println("查找的数值不存在");
            return -1;
        }
        if(key==list[mid]){
            return mid;
        }
        else if(key<list[mid]){
            return search(list,key,start,mid);
        }
        else{
            return search(list,key,mid,end);
        }

    }

    public static void main(String[] args) {
        int []list={1,2,3,4,5};
        int theIndex= BinarySearch.search(list,4,0,list.length-1);
        System.out.println("要查找的值的下标是"+theIndex);
    }
}
