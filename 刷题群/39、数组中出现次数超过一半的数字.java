遍历一遍、用哈希记录的话，时间和空间复杂度都是O(n); 先快速排序然后取数组中间的元素的话，时间复杂度Nlog(n),空间O(1).

这题应该用摩尔投票法（摩尔投票是用于寻找占一半以上的数），说白了既然一半以上是我的，那么我就让不一样的数字相互换掉，那么最后剩下的一定是我的兵（最差情况就是所有其他数字都跟我撞，我的数量大于一半所以
最后还是会剩下我；好一些的情况是其他不同势力的敌人之间撞，那么剩下的也是我的）
（注意当重新调整tag的值时，初始分数别忘了加1.我当时就忘了所以提交没通过）
class Solution {
    public int majorityElement(int[] nums) {
        int score=0;int tag=nums[0];
        for(int i=0;i<nums.length;i++){
            if(score==0) {
                tag=nums[i];
                score++;
            }
            else {
                if(tag==nums[i])score++;
                else score--;
            }
        }
        return tag;
    }
}
