这题如果无脑遍历就是O（n*n）的时间复杂度。
可以发现，遍历的过程中是存在重复运算的，比如结果数组是res的话，那么你计算res[i]的时候，是让原始数组i下标之前的所有元素相乘，
然后再乘上原始数组i下标之后的所有元素相乘；那么你下次计算res[i+1]时，原始数组i+1下标之前所有的元素相乘的计算，还是从头计算的，所以大量重复。

所以用空间换时间，申请数组来记录中间结果。因为res[i]的值就等于左部分（a[i]之前所有元素相乘）乘上右部分（a[i]之后所有元素相乘），因此我们
新建两个数组left和right来保存中间结果，left[i]代表a[i]之前所有元素相乘，right[i]代表a[i]之后所有元素相乘，因此a[i]=left[i]*right[i]
所以我们只需要遍历两边填好left和right这两个数组，就可以通过对应位相乘再遍历一遍得到结果数组。时间复杂度O（n），空间复杂度O（n）。
class Solution {
    public int[] constructArr(int[] a) {
        if(a.length==0)return new int[]{};
        int[]res=new int[a.length];
        int[]left=new int[a.length];
        int[]right=new int[a.length];
        left[0]=1;
        for(int i=1;i<left.length;i++) left[i]=left[i-1]*a[i-1];
        right[right.length-1]=1;
        for(int i=right.length-2;i>=0;i--) right[i]=right[i+1]*a[i+1];
        for(int i=0;i<res.length;i++) res[i]=left[i]*right[i];
        return res;
    }
}
