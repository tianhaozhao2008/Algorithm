这题说白了就是使输入的数组满足两个条件：
1.除了0可以重复意外，数组中的其它元素不能重复。
2.找到数组中的最大值和最小值（0不算，如果有最大值和最小值的话），要使max-min<=4

方法1：
因此遍历一遍找最大值最小值，是O（n），再弄个双层循环找是否有重复的，是O（n*n）即(1+n)*(n/2)。所以总的时间复杂度是O（n*n），空间复杂度是O（1）
class Solution {
    public boolean isStraight(int[] nums) {
        int max=0; int min=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max) max=nums[i];
            if(min==0 || (nums[i]!=0 && nums[i]<min)) min=nums[i];
                
            if(nums[i]!=0){
                for(int j=i+1;j<nums.length;j++){
                    if(nums[i]==nums[j]) return false;
                }
            }
        }
        if(max!=0 &&min!=0 &&max-min>=5) return false;
        return true;
    }
}

方法1改版：时间复杂度O（n*logn），空间复杂度O（1）。就是直接Arrays.sort排序后O（n）遍历找是否相同。
但是这样就修改了原始的nums数组。


方法2：如果找是否有重复的时候，弄个集合来装，那么时间复杂度是O（n），空间复杂度是O（1）。
class Solution {
    public boolean isStraight(int[] nums) {
        int max=0; int min=0;
        Set<Integer>set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max) max=nums[i];
            if(min==0 || (nums[i]!=0 && nums[i]<min)) min=nums[i];
            
            if(nums[i]!=0){
                if(set.contains(nums[i])) return false;
                set.add(nums[i]);
            }
        }
        if(max!=0 &&min!=0 &&max-min>=5) return false;
        return true;
    }
}
