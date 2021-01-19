动态规划的状态我是想到如何定义了，没想到的是把m最为最近出现的当前字符的下标，通过比较m和dp[n-1]的大小来决定状态如何转移，以用hash表记录最近字符出现的位置。

这题暴力遍历就是O（n^2）复杂度。动态规划的话，设状态dp[n]是以n结尾的无重复字符的字符串的最大长度，那么要看dp[n]是多少，就看前dp[n-1]个字符中是否包含num[n]，
如果不包含的话，dp[n]=dp[n-1]+1; 如果包含的话(设上一个与当前相同字符的下标是m)，那么dp[n]=n-m。
因此我们只需要找到m下标即可，如果每次都是遍历一遍前面的话，每次就需要O（n），那总体的时间复杂度又是O（n^2）了。。我就卡到这不知如何改进了。

看题解说是用HashMap，在每次遍历的时候都存一下当前字符最后出现的位置即可，然后每次直接查哈希表即可。
因为字符的数量是有限的（共有128种字符），哈希表最多就存那么多字符，所以空间复杂度还是O（1）。时间复杂度是O（n）。

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0)return 0;
        if(s.length()==1)return 1;
        int a=1; int b=1; int max=1;
        HashMap<Character,Integer> map=new HashMap<>();
        map.put(s.charAt(0),0);
        for(int i=1;i<s.length();i++){
            if(!map.containsKey(s.charAt(i)) ||i-map.get(s.charAt(i))>a) b=a+1;
            else b=i-map.get(s.charAt(i));
            a=b;
            if(b>max) max=b;
            map.put(s.charAt(i),i);
        }
        return max;
    }
}
