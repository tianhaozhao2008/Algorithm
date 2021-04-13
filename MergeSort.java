这个没什么难的，不像快排和选择有坑。
这个就是先sort然后合并即可。不用注意应该是不会写错的呵呵。

void sort(int[]nums,int left,int right){
        if(left>=right) return;
        int mid=(left+right)/2;
        sort(nums,left,mid);
        sort(nums,mid+1,right);

        int[]temp=new int[right-left+1];
        int i= left;
        int j=mid+1;
        for(int m=0;m<temp.length;m++){
            if(i==mid+1){
                temp[m]=nums[j];
                j++;
            }
            else if(j==right+1){
                temp[m]=nums[i];
                i++;
            }
            else if(nums[i]<nums[j]){
                temp[m]=nums[i];
                i++;
            }
            else {
                temp[m]=nums[j];
                j++;
            }
        }
        int start=left;
        for(int x:temp){
            nums[start]=x;
            start++;
        }
    }
