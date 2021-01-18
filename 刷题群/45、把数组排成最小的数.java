这题找规律的，规律其实就是先把int数组转换成字符串数组后，对该数组进行快排，然后把排序好的数组（从小到大）拼成String输出即可。
对于字符串的排序规则是：
1.如果两个字符串的length相同，就从左到右看大小直接比（字符相比就是比的编码，但数字小的对应的编码小，所以可以直接比）。
2.如果两个字符串m和n的length不同，那么就用a（m+n）代表m，用b （n+m）代表n。 （字符串相加就代表直接拼接） 

class Solution {
    public String minNumber(int[] nums) {
        if(nums.length==0)return "";
        String[]num=new String[nums.length];
        for(int i=0;i<nums.length;i++){
            num[i]=String.valueOf(nums[i]);
        }
        sort(num,0,num.length-1);
        StringBuilder res=new StringBuilder();
        for(String x:num) res.append(x);
        return res.toString();
    }
    void sort(String[]num,int left,int right){
        if(left>=right)return;
        int i=left;int j=right;
        String tag=num[left];
        while(i<=j){
            while(compare(num[i],tag))i++;
            while(compare(tag,num[j]))j--;
            if(i<=j){
                String temp=num[i];
                num[i]=num[j];
                num[j]=temp;
                i++;
                j--;
            }
        }
        sort(num,left,j);
        sort(num,i,right);
    }
    boolean compare(String m,String n){  //如果m小于n则返回true,否则返回false
        String a; String b;
        if(m.length()==n.length()) {
            a=m; b=n;
        }
        else{
            a=m+n; b=n+m;
        }
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)<b.charAt(i))return true;
                else if(a.charAt(i)==b.charAt(i))continue;
                else return false;
            }
            return false;
    }
}
