给一个字符串，在字符串中找到第一个只出现一次的字符，
那么首先肯定要遍历一遍记录哪些字符是只出现一次的（记录在哈希表中），因此时间复杂度肯定是O（n）了，因为你必须遍历完才知道某个字符是否出现一次。
然后再遍历哈希表，找出第一个出现一次的字符，这里就要用到顺序哈希表LinkedHashMap了，遍历它的复杂度是O（1）。
遍历它的方法注意一下：哈希表中的每个元素都是Map.Entry< , >类型， 遍历的话要把哈希表用entrySet方法转换一下，然后得到的其中每个元素，用getKey和getValue来
获得键和值。

class Solution {
    public char firstUniqChar(String s) {
        if(s=="")return ' ';
        Map<Character,Boolean>map=new LinkedHashMap<>();
        for(int i=0;i<s.length();i++){
            if (!map.containsKey(s.charAt(i))) map.put(s.charAt(i),true);
            else map.replace(s.charAt(i),false);
        }
        for(Map.Entry<Character,Boolean> x:map.entrySet()){
            if(x.getValue()==true)return x.getKey();
        }
        return ' ';
    }
}
