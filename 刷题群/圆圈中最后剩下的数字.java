


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
