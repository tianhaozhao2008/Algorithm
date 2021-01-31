找出数组中重复的数字。
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3 


思路
我想到几种方法。
1.如果只要求时间复杂度的话，就可以遍历这个数组，分别把每个元素加入到一个哈希集合中，如果加入的时候显示集合中已经有这个元素了，就说明这个元素是重复的。时间复杂度O(n),空间复杂度O(n).
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(hashSet.contains(nums[i])){
                return nums[i];
            }
            else{
                hashSet.add(nums[i]);
            }
        }
        return -1;
    }
}
2.如果只要求空间复杂度的话，就是遍历数组，两层循环：对于数组的每个元素，都对它之后的元素遍历一遍，看是否有一样的。空间复杂度O(1)，时间复杂度应该是n^2级别的。
class Solution {
    public int findRepeatNumber(int[] nums) {
        for(int i=0;i<nums.length-1;i++){
            for(int j=i+1;j<nums.length;j++){
                if(nums[i]==nums[j]){
                    return nums[i];
                }
            }
        }
        return -1;
    }
}
3.或者先快速排序，然后遍历一遍，看相邻的是否相同。这个空间复杂度O(1)，时间复杂度O(nlogn)
  class Solution {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                return nums[i];
            }
        }
        return -1;
    }
}

4.一种空间O(1),时间O(n)的方法。题目说了数组长度n，所有数字都在0~n-1，
那么就是如果没有重复的话，就可以一个萝卜对应一个坑（元素和下标相等）我们一个萝卜一个坑，对于0号坑，比如是1号萝卜，就把0号坑和1号坑交换；
直到0号找到自己的0号萝卜，然后去下一个坑重复上述操作；

如果有重复值的话，比如对于3号坑，没有对应的3号萝卜，那么它就换来换去，一定会出现比如此时3号坑里是4号萝卜，现在把4号换给四号坑，发现4号坑里也有4号萝卜，就是有两个4号，
那么就是发现重复的萝卜了。
class Solution {
    public int findRepeatNumber(int[] nums) {

        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                int x = nums[nums[i]];
                if(nums[nums[i]]==nums[i]){
                    return nums[i];
                }
                else{
                    nums[nums[i]]=nums[i];
                    nums[i]=x;
                }
            }
        }
        return -1;
    }
}




