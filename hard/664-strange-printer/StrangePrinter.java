class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int len = 1; len <= n; ++len) {
            for (int left = 0; left <= n-len; ++left) {
                int r = left + len - 1;
                int j = -1;
                dp[left][r] = n;
                for (int i = left; i < r; ++i) {
                    if (s.charAt(i) != s.charAt(r) && j == -1) {
                        j = i;
                    }
                    if (j != -1) {
                        dp[left][r] = Math.min(dp[left][r], 1 + dp[j][i] + dp[i+1][r]);
                    }
                }
                if (j == -1) dp[left][r] = 0;
            }
        }

        return dp[0][n-1]+1;
    }
}