这题狗得很，这题应该换成困难。
如果用环状链表模拟，时间复杂度就是O（n*m），空间复杂度O（n）。
如果用数组模拟，如果删除元素的话标为-1，那么时间复杂度还是O（n*m），因为每次遍历要删除的元素时都要判断是否为-1。


下面这种是找规律、找递推公式，dp[n]表示当还有n个元素存在时，最终能存留的那个元素的下标。从左到右数、第一个报数的在第一个。
可以发现，当有n个人时，一个人死了，那么下次n-1人时死的人的下一个就会排在第一个位置（0号下标），也就是说每死个人，后面的都要左移m个单位。
然后当有n个人时数组长度就是n。所以，如果用dp[n-1]推dp[n],就让dp[n-1]往右移m个单位，（由于可能越界，因为长度最长就是n，所以要%m），
因此，dp[n]=(dp[n-1]+m)%n. ok,动态规划。
class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();
        int x=solution.lastRemaining(5,3);
    }
    public int lastRemaining(int n, int m) {
        int dp=0; //剩一个人时，最终的幸存者的下标是0
        for(int i=2;i<=n;i++){
            dp=(dp+m)%i;
        }
        return dp;
    }
}
