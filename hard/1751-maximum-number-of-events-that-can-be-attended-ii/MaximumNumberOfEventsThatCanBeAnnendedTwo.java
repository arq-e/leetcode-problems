class Solution {
    public int maxValue(int[][] events, int k) {
        int n = events.length;
        Arrays.sort(events, (a,b) -> a[0]-b[0]);

        int[][] dp = new int[n][k+1];

        int res = recursion(events, dp, k, 0);
        return res;
    }

    private int recursion(int[][] events, int[][] dp, int k, int pos) {
        if (k == 0) return 0;
        if (dp[pos][k] == 0) {
            int next = search(events, events[pos][1]+1);
            if (pos + 1 >= events.length ) {
                dp[pos][k] = events[pos][2];
            } else if ( next >= events.length) {
                dp[pos][k] = Math.max(recursion(events,dp,k,pos+1), events[pos][2]);
            } else dp[pos][k] = Math.max(recursion(events,dp,k, pos+1), events[pos][2] + recursion(events,dp,k-1,next));
        }
        return dp[pos][k];
    }

    private int search(int[][] events, int val) {
        int left = 0;
        int right = events.length;

        while (left < right) {
            int med = (left + right) / 2;
            if (events[med][0] < val) {
                left = med + 1;
            } else if (events[med][0] >= val) {
                right = med;
            } else {
                left = med;
                break;
            }
        }
        return left;
    }
}