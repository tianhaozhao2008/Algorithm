给定两个数组，编写一个函数来计算它们的交集。

 

示例 1：

输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2]
示例 2：

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[9,4]
 

说明：

输出结果中的每个元素一定是唯一的。
我们可以不考虑输出结果的顺序。


方法1.首先想到是对于第一个数组的每一个元素，都去第二个数组遍历一遍找是否有相同的，时间复杂度是O(n2)
方法2.优化一下，先对两个数组用快排，然后再用方法1中那样，对于第一个数组的每一个元素，都去第二个数组找，只不过第二个数组一共只需要遍历一次了（因为排序了）。时间复杂度O(nlogn)
方法3.就是如下面这种，分别把两个数组装入连个集合中（为了利用集合的Hash散列，插入和查找基本是O(1)，其实这里改一下只把一个数组放set里，然后遍历另一个就可以了。。），
然后对于第一个集合的每一个元素都去第二个集合查找是否有相同的。时间复杂度O(n)

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for(int x:nums1){
            set1.add(x);
        }
        Set<Integer> set2 = new HashSet<>();
        for(int x:nums2){
            set2.add(x);
        }

        List<Integer> list = new ArrayList<>();
        for(int x:set1){
            if(set2.contains(x)){
                list.add(x);
            }
        }
        int result[]=new int[list.size()];
        for(int i =0;i<list.size();i++){
            result[i]=list.get(i);
        }
        return result;
    }
}
