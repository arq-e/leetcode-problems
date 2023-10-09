class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[][] dp = new int[n][m];
        
        dp[0][0] = nums1[0]*nums2[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(nums1[i]*nums2[0], dp[i-1][0]);
        }
        for (int j = 1; j < m; ++j) {
            dp[0][j] = Math.max(nums1[0]*nums2[j], dp[0][j-1]);
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                int add = Math.max(dp[i-1][j-1] + nums1[i]*nums2[j], nums1[i]*nums2[j]);
                int skip = Math.max(dp[i-1][j], dp[i][j-1]);
                dp[i][j] = Math.max(add, skip);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, dp[i][m-1]);
        }
        for (int j = 0; j < m; ++j) {
            max = Math.max(max, dp[n-1][j]);
        }

        return max;
    }
}
