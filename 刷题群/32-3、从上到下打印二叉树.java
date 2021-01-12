（这题要画图的话，注意栈和队列在一起的时候，就按照栈那么画，它们都是从上面进入，只是栈是从上面拿走，队列是从下面漏走）
方法1：这题我在想设个flag，即一层是先放左后放右，然后下一层就是先放右后放左。
然后还是采取上一题用队列那样做，结果tm不对。

然后我就把队列改成按栈那样做（栈和队列的进入方式是一样的，只是出去的方式不一样。由于栈的出入点都在同一个方向，所以我需要让栈排空之后，才允许入栈，
所以需要先用个队列承装栈排出来的元素，都排出来之后，再把队列储存的元素的子元素依次压栈）
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)return res;
        Deque<TreeNode> deque=new LinkedList<>();
        deque.offer(root);
        int tag=1; //区分奇偶层

        while(deque.size()!=0){  //对于每一行
            int size= deque.size();
            List<Integer>midRes=new ArrayList<>();//放每一行的数值
            Queue<TreeNode> midQue=new LinkedList<>(); //暂时存放一行的所有节点
            //由于是出栈操作，要都出完栈后才能入栈（入栈和入队操作一样），所以需要用一个中间队列保存出队的所有节点
            for(int i=1;i<=size;i++){ //取出一行的所有节点（出栈），把数值存入那个中间数组，把节点也存入中间队列。
                TreeNode node=deque.pop();
                midRes.add(node.val);
                midQue.offer(node);
            }
            res.add(midRes);
            //接下来把下一行的所有节点入栈
            while(midQue.size()!=0){
                TreeNode node=midQue.poll();
                if(tag==1){
                    if(node.left!=null)deque.push(node.left);
                    if(node.right!=null)deque.push(node.right);
                }
                else{
                    if(node.right!=null)deque.push(node.right);
                    if(node.left!=null)deque.push(node.left);
                }
            }
            tag=-tag;
        }
        return res;
    }
}

方法2：就是还是按照上一题做，只不过奇偶分离，奇数就逆转中间结果即可。 
如果还是按照上一题那样用ArrayList的话，逆转就很慢因为底层是数组，但是看题解就很不讲武德：直接把ArrayList换成LinkedList，
因为它们都实现了List接口，所以符合题意。我擦。
