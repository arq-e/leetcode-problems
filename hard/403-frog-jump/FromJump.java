class Solution {
    public boolean canCross(int[] stones) {
        if (stones[1] != 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < stones.length; ++i) {
            map.put(stones[i], i);
        }

        int[][] dp = new int[stones.length][stones.length];
        dp[0][1] = 1;
        dp[1][0] = 1;
        for (int i = 0; i < dp.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if(dp[i][j] > 0) {
                    if (i == dp.length - 1) return true;
                    int start = stones[i];
                    for (int k = dp[i][j] - 1; k <= dp[i][j] + 1; ++k) {
                        if (map.containsKey(start + k)) {
                            dp[map.get(start+k)][i] = k;
                        }
                    }
                }
            }
        }

        return false;
    }
}