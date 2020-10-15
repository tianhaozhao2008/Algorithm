给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

输入: [2,2,1]
输出: 1
示例 2:

输入: [4,1,2,1,2]
输出: 4


1.我第一遍的代码，用了一个哈希集合，会占用部分额外空间，算法复杂度和空间复杂度都是O(n)，但不知道为什么运行时间只打败8%的人。
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


2.改成不使用额外空间，就在数组中进行两层for循环，代码写得很乱。。算法复杂度是O(n^2),空间复杂度O(1)
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

3.我在想可以在第2中方法的基础上，首先对数组用快速排序搞一次，然后扫描一遍for(int i=0;i<nums.length;i+=2),看是否相邻的一样
那么此时的算法复杂度O(nlogn),空间复杂度O(1)。具体代码不写了。
    

    
4.即最终的方法，算法复杂度和空间复杂度都是O（1）。

思路就是看到这种涉及到两两相同的，可以想到位运算中的亦或（即如果两两相同则为0，不同则为1.），
这个位运算是先把十进制转换成二进制，运算完毕后再转换回来。亦或运算满足如下性质：
(1).任何数和 0做异或运算，结果仍然是原来的数，即 a⊕0=a。
(2).任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
(3).异或运算满足交换律和结合律，即a⊕b⊕a = b⊕a⊕a = b⊕(a⊕a) = b⊕0 = b。

因此答案显而易见了，即对数组中的所有元素都做亦或运算，结果即答案。
class Solution {
    public int singleNumber(int[] nums) {
        int answer=0;
        for(int x: nums){
            answer=answer^x;
        }
        return answer;
        
    }
}

    
    
