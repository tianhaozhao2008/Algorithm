剑指 Offer 11. 旋转数组的最小数字  https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  

示例 1：
输入：[3,4,5,1,2]
输出：1

示例 2：
输入：[2,2,2,0,1]
输出：0

就是输入的数组是对升序数组旋转后的，具有一定性质，简单的二分法。。

迭代的版本：边界条件和特殊情况很多。注意下（如数组就没反转、或很多重复值，start和middle处的值一样导致你无法判断最小值在左边还是右边）
class Solution {
    public int minArray(int[] numbers) {
        if(numbers[0]<numbers[numbers.length-1]){ //特殊情况，如果就没有反转的话
            return numbers[0];
        }
        int tag=numbers[0];
        int start=0; int end=numbers.length-1;
        while(true){
            int middle=(start+end)/2;
            if(middle==start){ //基本结束条件,即当end比start大1时
                if(numbers[start]<numbers[end]){
                    return numbers[start];
                }
                else{
                    return numbers[end];
                }
            }
            if (tag<numbers[middle]){
                start=middle;
            }
            else if(tag>numbers[middle]){
                end=middle;
            }
            else{ //此时tag==numbers[middle]，因此无法辨别最小值在左边还是在右边，
                  // 因此应该当成左边弄一次再当成右边弄一次，看哪个小。但是这个程序这种写法实现不了，改成递归就可以了。
                int min=numbers[start];
                for(int i=start;i<=end;i++){
                    if(numbers[i]<min){
                        min=numbers[i];
                    }
                }
                return min;
            }
        }
    }
}

递归的版本：



