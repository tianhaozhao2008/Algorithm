这题狗得很，这题应该换成困难。
如果用环状链表模拟，时间复杂度就是O（n*m），空间复杂度O（n）。
如果用数组模拟，如果删除元素的话标为-1，那么时间复杂度还是O（n*m），因为每次遍历要删除的元素时都要判断是否为-1。
先写个自创链表的方法：
class Node{
    int value;
    Node next;
    Node(int value){
        this.value=value;
    }
}
class Solution {
    public int lastRemaining(int n, int m) {
        Node head=new Node(0);
        Node x=head;
        Node last=x; //这块其实不用初始化，但是后面非说might not be initial，所以随便弄一个
        //下面for循环是创建整个链表
        for(int i=1;i<n;i++){
            x.next=new Node(i);
            x=x.next;
            if(i==n-1) {
                x.next=head;
                last=x;
            }
        }
        if(m==1){  //m==1时单独考虑，因为后面的代码在m=1时用不了
            for(int i=1;i<=n-1;i++){  //删除n-1次，最后只留下一个
                last.next=last.next.next;
            }
            return last.value;
        }
        x=head;
        for(int i=1;i<=n-1;i++){   //删除n-1次，最后只留下一个
            for(int j=1;j<=m-2;j++){  //前移m-1次
                x=x.next;
            }
            x.next=x.next.next;
            x=x.next;
        }
        return x.value;
    }
}


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
