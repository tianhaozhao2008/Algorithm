这快排十分的烦人，自己写的边界条件搞半天一直bug（tm的不知道当初是怎么一次写对的，我真是颓了。）
直接死记了，不然面试也没时间让你在那慢慢试边界。
就是三个while 两个if，中间那两个while是没等号的，其它都有等号。     然后记着最后那个if也要i++和j++。 选标志元素的话选最左边的还是中间的都没影响，就是分成两部分而已。

三个while 两个if 两个i++和j--

void sort(int[]arr,int left,int right){
    if(left>=right)return;
    int i=left;int j=right;
    //int tag=arr[(left+right)/2];
    int tag=arr[right];
    while(i<=j){
        while(arr[i]<tag)i++;
        while(arr[j]>tag)j--;
        if(i<=j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
    }
    sort(arr,left,j);
    sort(arr,i,right);
}
