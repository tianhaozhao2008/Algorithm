class Solution {
    public void sort(int[]list,int left,int right){
        if(left>=right) return;
        int mid=(left+right)/2;
        sort(list,left,mid);
        sort(list,mid+1,right);
        
        int []temp=new int[right-left+1];
        int i=left; 
        int j=mid+1;
        for(int m=0;m< temp.length;m++){
            if(i==mid+1){
                temp[m]=list[j];
                j++;
                continue;
            }
            if(j==right+1){
                temp[m]=list[i];
                i++;
                continue;
            }
            if(list[i]<list[j]){
                temp[m]=list[i];
                i++;
            }
            else{
                temp[m]=list[j];
                j++;
            }
        }
        for(i=0;i< temp.length;i++){
            list[left]=temp[i];
            left++;
        }
    }
}
