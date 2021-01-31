这题也很难：
1.涉及函数中，调用自身的语句在一个循环体内；
2.以及递归函数内定义的一个集合（这种变量只在当前函数执行时起作用，调用下一个自身时，当前变量等信息就压栈了，就是下一个递归函数中的定义的变量起作用）；
3.还涉及交换不同位置的值，递归调用自身后再换回来（如果不换的话就会出问题，因为操作的那个数组是个全局变量，
 你第一次调用的递归函数不换没事，继续调用自身后，还不换的话就会出问题）。
 
 思路就是比如abcde，那么第一个节点有五种可能，第一个节点固定之后，第二个节点有四种可能。。
 那么固定到的位数就是递归函数的参数，比如第一个节点的话就是for循环分别交换第一个节点与其它节点，并调用自身，调用完自身后再换回来。
 防止重复值就是循环体内判断交换的元素有没有弄过，因此在递归函数内定义一个哈希集合（该集合的作用域只在当前函数执行时生效）。
 这题是返回字符串数组，因为我们是不断往里面添加元素的，我首先想的是用个ArrayList，但是发现要添加的结果还不少，就涉及扩容了，所以换成LinkedLsit，
 最后再转换成String[]即可（他俩转换成数组的复杂度应该是一样的，都遍历一遍复制。就是注意一下链表转换成数组的方式）
 
 class Solution {
    List<String> res=new LinkedList<>();
    char[] c; //就在这个字符数组上操作拼接了。
    
    public String[] permutation(String s) {
        c=s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int n){
        if(n==c.length-1){
            res.add(String.valueOf(c));
            return;
        }
        HashSet<Character>set=new HashSet<>();
        for(int i=n;i<c.length;i++){
            if(set.contains(c[i]))continue;
            set.add(c[i]);
            swap (n,i);
            dfs(n+1);
            swap(i,n);
        }
    }
    void swap(int i,int j){
        char temp=c[i];
        c[i]=c[j];
        c[j]=temp;
    }
}
