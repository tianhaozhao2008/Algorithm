这题竟然是动态规划。。这种概率的题，我一开始是想着直接用概率相乘来计算，那样的话就直接跑偏了。
要想用动态规划就得用古典概型（每种可能性的概率相等，然后通过求比来算出概率）算，转换成出现次数的可能性，然后除上所有的可能性（6的n次方）。
因为只有这样才能得到状态的转移关系，依赖于上一种状态。

我一开始想着是dp[s]就是和为s的概率，应该变成次数，比如扔4次筛子的话，dp[24]就代表点数和为24时的情况数量，此时只有一种情况，即四个都是6；
dp[23]代表点数和为23时的情况数量，此时又四种情况，即三个6和一个5，很难发现状态转移的规律。
如果很难发现规律的话，可以再加一维，dp[n][s]代表n个筛子的总和为s的情况，那么dp[n][s]= dp[n-1][s-6] + dp[n-1][s-5] + dp[n-1][s-4] +..+
  dp[n-1][s-1]  (当 s-i>=n-1 && s-i<=6（n-1）如果不符合就continue)。就找到关系了。
对于二维数组dp，我们把n看成每一行的话，那么只要保存一行就行了。n个筛子的话点数和的范围就是n~6n，但是n之前的状态时点数和的范围小，最小时是
1个筛子和为1时，所以我们的数组的长度直接弄成0~6n好了。

