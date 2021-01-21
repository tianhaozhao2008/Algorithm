暴力方法：这题是改成了一个数出现一次，其它所有数出现三次，找出出现一次的数。最暴力的就是遍历一遍，在HashMap记录所有数出现次数，
然后遍历哈希表找到出现一次的数。时间复杂度O（n），即遍历n+n/3遍。空间复杂度O（n），即哈希表存n/3的数据。
（更暴力的是两个for循环直接找有无重复的，那么时间是O（n*n），空间是O（1））
class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Boolean> map=new HashMap<>();
        for(int x:nums){
            if(map.containsKey(x)) map.put(x,false);
            else map.put(x,true);
        }
        for(Map.Entry<Integer,Boolean> x:map.entrySet()){
            if(x.getValue()==true)return x.getKey();
        }
        return -1;
    }
}

最佳方法：一般涉及到有重复元素和无重复元素的题，可以联想到位运算。重复两次的话，就可以联想到亦或后是0、0与任何数亦或的结果还是任何数、亦或
满足交换律。。如果是出现多次重复的：
比如这题是3次重复，然后只有一个数是1次重复，那么可以想到：一个数32位，对于每一位，把所有数的这一位的值（0或1）都加起来后再除3，余数就是单独的
1次重复的那个数的值。
计算不同位的值是0还是1，或者告诉你32位各个位是0 or 1然后让你把它变成int，套路都是与1操作，然后不断左移右移来完成。
