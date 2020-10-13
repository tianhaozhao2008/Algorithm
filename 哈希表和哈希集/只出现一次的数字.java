给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4


1.我第一遍的代码，用了一个哈希集合，会占用部分额外空间，复杂度O(n)，但不知道为什么运行时间只打败8%的人。
class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int x: nums){
            if(set.contains(x)){
                set.remove(x);
            }
            else{
                set.add(x);
            }
        }
        int[]answer = new int[1];
        for(int x:set){
            answer[0]=x;
        }
        return answer[0];
    }
}


2.改成不使用额外空间，就在数组中进行两层for循环，代码写得很乱。。
class Solution {
    public int singleNumber(int[] nums) {
        int answer = 10;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=-123){
                for(int j=i+1;j<nums.length;j++){
                    
                    if(nums[j]==nums[i]){
                        nums[j]=-123;
                        nums[i]=-123;
                        break;
                    }
                    if(j==nums.length-1){
                        answer=nums[i];
                        return answer;
                    }
                }
                if(i==nums.length-1){
                    return nums[i];
                }
                //int answer=nums[i];
            }

        }
        return answer;
    }
}


