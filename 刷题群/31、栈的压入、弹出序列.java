弄一个栈，然后两个指针分别指向两个数组，模拟操作，看每一步是否相符。比较简单。
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if(pushed.length!=popped.length)return false;
        if(pushed.length==0)return true;
        Deque<Integer> stack=new LinkedList<>();
        int j=0; //j是指向popped数组起始位置
        for(int i=0;i<pushed.length;i++){  //i指向pushed数组的位置
           stack.push(pushed[i]);
           while(j<popped.length&&stack.size()!=0&&popped[j]==stack.peek()){
               stack.pop();
               j++;
           }
        }
        if(stack.size()!=0){
            for(int k=j;k<popped.length;k++){
                if(stack.pop()!=popped[k])return false;
            }
        }
        return true;
    }
}
