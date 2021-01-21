这题我是用滑动窗口，比如输入的是9，那么我就开辟一块（9+1）/2长度的数组，填上1，2，3，4，5，然后弄两个指针，
left指向1，right指向2，那么分三种情况：
while（left<right）:
1.如果当前left和right的区间的和正好等于target，那么就Arrays.copyOfRange方法把子数组拷贝到结果集中，然后调一下使指针前进一格（我选的是left前进）
2.如果当前left和right的区间的和小于target，就让right前进一格，这样和就会变大。
3.如果当前left和right的区间的和大于target，就让left前进一格，这样区间变小、和就会变小。

空间复杂度开辟了一块数组空间，是O（n），占了n/2的空间。
时间复杂度两个指针一直移动，就是O（n）了，但是每次都计算区间的和，即要遍历区间，这个区间长度应该是和n成正比的，所以整体就是O（n*n）了。
等会我改进一下计算区间和那块，使其不重复计算。从而把时间复杂度优化成O（n）。

class Solution {
    public int[][] findContinuousSequence(int target) {
        if(target<=2)return new int[][]{};
        int[]input=new int[(target+1)/2];
        for(int i=0;i<input.length;i++) input[i]=i+1;
        int left=0;
        int right=1;
        List<int[]> res=new ArrayList<>();
        while(left<right &&right< input.length){
            int sum=0;
            for(int i=left;i<=right;i++) sum+=input[i];
            if(sum==target){
                res.add(Arrays.copyOfRange(input,left,right+1));
                left++;
            }
            else if(sum<target)right++;
            else left++;
        }
        int[][]output=new int[res.size()][];
        for(int i=0;i<res.size();i++){
            output[i]=res.get(i);
        }
        return output;
    }
}
