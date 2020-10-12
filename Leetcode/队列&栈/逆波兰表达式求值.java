class Solution {

    public static int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();
        Set<String> hashset = new HashSet<>(){{
            addAll(Arrays.asList(new String[]{"1","2","3","4","5","6","7","8","9","0"}));
        }};
        for(String x:tokens){
            if(hashset.contains(x)){
                stack.push(x);
            }
            else{
                int a = Integer.valueOf(stack.pop());
                int b = Integer.valueOf(stack.pop());
                int result;
                if(x=="+"){
                    result= a+b;
                }
                else if(x=="-"){
                    result=b-a;
                }
                else if(x=="*"){
                    result=a*b;
                }
                else{
                    result=b/a;
                }
                stack.push(String.valueOf(result));
            }
        }
        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        String[]a = {"2","1","+","3","*"};
        int b= evalRPN(a);
        System.out.println(b);
    }
}
